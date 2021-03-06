package cubex.mahesh.json_oct7am

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insert.setOnClickListener {
            var fis:FileInputStream = openFileInput("employees.json")
            var emps: Employees? = null
            var list :MutableList<Employee>? = null
            if(fis.available()==0){
                var e = Employee(et1.text.toString().toInt(),
                        et2.text.toString(),et3.text.toString(),
                        et4.text.toString())
                 list = mutableListOf<Employee>()
                list.add(e)
                 emps = Employees(list)
            }else{
                var g = Gson(  )
                var reader = InputStreamReader(fis)
                 emps  =     g.fromJson(reader,Employees::class.java)
                 list = emps.employees
                var e = Employee(et1.text.toString().toInt(),
                        et2.text.toString(),et3.text.toString(),
                        et4.text.toString())
                list.add(e)
            }
                var g = Gson( )
                var json_data:String = g.toJson(emps)
                var fos:FileOutputStream = openFileOutput(
                        "employees.json", Context.MODE_PRIVATE)
                fos.write(json_data.toByteArray())
                fos.flush()
                fos.close()

        }
        read.setOnClickListener {
            var fis:FileInputStream = openFileInput("employees.json")
                var g = Gson(  )
                var reader = InputStreamReader(fis)
            var emps  =     g.fromJson(reader,Employees::class.java)
            var list = emps.employees
            var temp_list = mutableListOf<String>()
            for(e in list)
            {
                temp_list.add(e.id.toString() +"\t" + e.name +"\n" +
                                            e.desig+"\t"+e.dept)
            }
            var adapter = ArrayAdapter<String>(this@MainActivity,
                    android.R.layout.simple_spinner_item,temp_list)
            lview.adapter = adapter
        }
    }
}
