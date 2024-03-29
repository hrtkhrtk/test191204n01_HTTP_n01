package test.test191204n01_http_n01

import android.app.Activity
import android.net.Uri
import android.os.AsyncTask
import android.os.AsyncTask.execute
import android.util.Log
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.OkHttpClient

import com.fasterxml.jackson.databind.ObjectMapper
import jp.pay.android.Payjp
import jp.pay.android.PayjpConfiguration
import okhttp3.Request
import java.util.*

class AsyncHttpRequest(private val mainActivity: Activity)// 呼び出し元のアクティビティ
    : AsyncTask<Uri.Builder, Void, String>() {

    // このメソッドは必ずオーバーライドする必要があるよ
    // ここが非同期で処理される部分みたいたぶん。
    override fun doInBackground(vararg builder: Uri.Builder): String {
        // httpリクエスト投げる処理を書く。
        val client = OkHttpClient()
        val MIMEType = MediaType.parse("application/json; charset=utf-8")
        val dataToSend_Map = HashMap<String, String>()
        dataToSend_Map["payjp-token"] = 123.toString()
        dataToSend_Map["price"] = 456.toString()
        dataToSend_Map["purchaseTo"] = 1576000000000.toString()
        dataToSend_Map["currentUID"] = "TCZazfEKrbUN8WMvl3j2qCEqsLI3"

        val mapper = ObjectMapper() // 参考：https://qiita.com/opengl-8080/items/b613b9b3bc5d796c840c
        val dataToSend_json = mapper.writeValueAsString(dataToSend_Map)

        Log.d("test191204n01", dataToSend_json)

        val url = "https://twitterclone-api-ver01.herokuapp.com/payment"

        val requestBody = RequestBody.create(MIMEType, dataToSend_json)
        val request = Request.Builder().url(url).post(requestBody).build()
        val response = client.newCall(request).execute()

        //return (response as String)
        return ""
    }


    // このメソッドは非同期処理の終わった後に呼び出されます
    override fun onPostExecute(result: String) {
        // 取得した結果をテキストビューに入れちゃったり
        //TextView tv = (TextView) mainActivity.findViewById(R.id.name);
        //tv.setText(result)
    }
}