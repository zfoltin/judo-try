package uk.co.tomek.judopayapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.judopay.Judo
import com.judopay.PaymentFragment
import com.judopay.model.Currency
import java.util.UUID.randomUUID

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = Bundle()
        bundle.putParcelable(Judo.JUDO_OPTIONS, Judo.Builder()
                .setApiToken(API_TOKEN)
                .setApiSecret(API_SECRET)
                .setJudoId(JUDO_ID)
                .setEnvironment(Judo.SANDBOX)
                .setConsumerReference(randomUUID().toString())
                .setAmount("0.05")
                .setCurrency(Currency.GBP)
                .build())

        val fragment = PaymentFragment()
        fragment.arguments = bundle

        fragmentManager
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Judo.JUDO_REQUEST) {
            when (resultCode) {
                Judo.RESULT_SUCCESS -> Log.d("Judopay", "Payment successful")
                Judo.RESULT_ERROR -> Log.e("Judopay", "Error during payment")
            }
        }
    }

    companion object {
        private const val JUDO_ID = "xxx"
        private const val API_TOKEN = "xxx"
        private const val API_SECRET = "xxx"
    }
}
