# EasyLogger

EasyLogger is my custom solution for console and file logging. The syntax is very similar to logging in Android Studio.

To write a log to the console, there are different methods:

&emsp;- `Log.d();` for debugging,

&emsp;- `Log.e();` for errors,

&emsp;- `Log.i();` for information,

&emsp;- `Log.v();` for verbose, 

&emsp;- `Log.w();` for warnings

All primitive types are supported.

### &emsp;Example One:
&emsp;&emsp;`Log.d("Hello World");`
 
&emsp;&emsp;Output to Console:

&emsp;&emsp;`[2022/10/21 11:53:44][DEBUG] Hello World`

### &emsp;Exaple Two:

&emsp;&emsp;`Log.i("Hello World");`

&emsp;&emsp;Output to Console:

&emsp;&emsp;`[2022/10/21 11:53:44][INFO] Hello World`

  
Optionally, it is possible to use tags when logging; this makes logging more searchable when troubleshooting:

### &emsp;Example One:

&emsp;&emsp;`Log.d("tag", "Hello World");`

&emsp;&emsp;Output to Console:

&emsp;&emsp;`[2022/10/21 11:53:44][DEBUG:tag] Hello World`

### &emsp;Example Two:

&emsp;&emsp;`Log.e("FileNotFound", "Hello_World.txt");`

&emsp;&emsp;Output to Console:

&emsp;&emsp;`[2022/10/21 11:53:44][ERROR:FileNotFound] Hello_World.txt`

To enable logging to a file simply do `Log.enableLogging()` at the start of the program. It will only save everything logged after it. Logs are saved to `user.home/logs/data.log`. Every time the program is run, it creates a new `data.log` file and saves the old log file in the format `yyyy_MM_dd.HH-mm-ss.data.log`.

Optionally, it is possible to save save logs to a custom log file by including a parameter: `Log.enableLogging("customFile.log");`. No spaces are allowed, and the filetype needs to be included, most commonly `.log`.
  
 
