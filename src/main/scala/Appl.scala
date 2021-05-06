import Colors.{cprint, cprintln}

object Appl {

    def main(args: Array[String]): Unit = {

        def choose(): Unit = {
            println("")
            print("   ____________________| ")
            cprint(Colors.YELLOW_BOLD,"SELECT FILE OR FOLDER")
            println(" |___________________")
            println("--'----------------------------------------------------------------'--")

            FileChooser.choose()
        }

        cprintln(Colors.YELLOW_BOLD, "Welcome in Bob: The duplicate files detector")
        cprint(Colors.GREEN_UNDERLINED, "Version")
        cprintln(Colors.GREEN, ": 1.1.0")
        choose()

        var f = true
        while(f) {
            println("")
            cprintln(Colors.BLUE, "Do you want to choose another? (y/N)")
            val response: String = scala.io.StdIn.readLine()

            if(response == "Y" || response == "y") choose()
            else f = false
        }
    }

}
