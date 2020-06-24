package cl.ecorrea.appfirebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    BASIC,
    GOOGLE
}
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setuo
        val bundle      =   intent.extras
        val email       =   bundle?.getString("email")
        val provider    =   bundle?.getString("provider")
        setup(email ?:"", provider ?:"")

        //Guardadode datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.putString("email",email)
            prefs.putString("provider",provider)
            prefs.apply()

    }

    private fun setup(email: String, provider: String){
        title ="Inicio"
        emailTextView.text = email
        providerTextView.text = provider

        //Borrar Datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

        //Cerrar sesion
        logOutButtom.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}