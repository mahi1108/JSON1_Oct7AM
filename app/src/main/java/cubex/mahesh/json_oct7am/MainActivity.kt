package cubex.mahesh.json_oct7am

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insert.setOnClickListener {
                var e = Employee(et1.text.toString().toInt(),
                                                et2.text.toString(),et3.text.toString(),
                                                et4.text.toString())
                var list = mutableListOf<Employee>()
                list.add(e)
                var emps = Employees(list)
                var g = Gson( )
                var json_data:String = g.toJson(emps)
                var fos:FileOutputStream = openFileOutput(
                        "employees.json", Context.MODE_PRIVATE)
                fos.write(json_data.toByteArray())
                fos.flush()
                fos.close()

        }
        read.setOnClickListener {

        }
    }
}
