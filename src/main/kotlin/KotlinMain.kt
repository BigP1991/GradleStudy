import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.methods.GetMethod
import java.io.File

fun main(args: Array<String>) {
    var client = HttpClient()
    var range = 1..2
    for (i in range) {
        var method = GetMethod("https://image.baidu.com")
        client.executeMethod(method)
        var responseBody = method.getResponseBody()
        method.releaseConnection()
        var file = File("${i}.jpg")
        file.writeBytes(responseBody)
    }
}
