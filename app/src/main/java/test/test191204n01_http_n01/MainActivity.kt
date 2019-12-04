package test.test191204n01_http_n01

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import jp.pay.android.Task
import jp.pay.android.model.Token
import jp.pay.android.ui.widget.PayjpCardFormFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mCardFormFragment = PayjpCardFormFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        purchaseButton.setOnClickListener{v ->
            // httpリクエストを入れる変数
            val builder = Uri.Builder()

            val task = AsyncHttpRequest(this)
            task.execute(builder)
        }
        */

        //cardFormFragment = PayjpCardFormFragment.newInstance()
        //val cardFormFragment = PayjpCardFormFragment.newInstance()
        supportFragmentManager.beginTransaction()
                //.replace(R.id.card_form_view, cardFormFragment, TAG_CARD_FORM)
                //.replace(R.id.card_form_view, mCardFormFragment as Fragment, TAG_CARD_FORM)
                .replace(R.id.card_form_view, mCardFormFragment as Fragment, "TAG_CARD_FORM")
                .commit()
        submitButton.setOnClickListener {
            onClickSubmit()
        }
    }

    //fun onClickSubmit() {
    private fun onClickSubmit() {
        //if (!cardFormFragment.validateCardForm()) return
        if (!mCardFormFragment.validateCardForm()) return
        //cardFormFragment.createToken().enqueue(object : Task.Callback<Token> {
        mCardFormFragment.createToken().enqueue(object : Task.Callback<Token> {
            override fun onSuccess(data: Token) {
                Log.i("CardFormViewSample", "token => $data")
            }

            override fun onError(throwable: Throwable) {
                Log.e("CardFormViewSample", "failure creating token", throwable)
            }
        })
    }
}
