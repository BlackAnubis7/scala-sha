object Colors {
    // Reset
    val RESET = "\u001b[0m" // Text Reset


    // Regular Colors
    val BLACK = "\u001b[0;30m" // BLACK

    val RED = "\u001b[0;31m" // RED

    val GREEN = "\u001b[0;32m" // GREEN

    val YELLOW = "\u001b[0;33m" // YELLOW

    val BLUE = "\u001b[0;34m" // BLUE

    val PURPLE = "\u001b[0;35m" // PURPLE

    val CYAN = "\u001b[0;36m" // CYAN

    val WHITE = "\u001b[0;37m" // WHITE


    // Bold
    val BLACK_BOLD = "\u001b[1;30m"
    val RED_BOLD = "\u001b[1;31m"
    val GREEN_BOLD = "\u001b[1;32m"
    val YELLOW_BOLD = "\u001b[1;33m"
    val BLUE_BOLD = "\u001b[1;34m"
    val PURPLE_BOLD = "\u001b[1;35m"
    val CYAN_BOLD = "\u001b[1;36m"
    val WHITE_BOLD = "\u001b[1;37m"

    // Underline
    val BLACK_UNDERLINED = "\u001b[4;30m"
    val RED_UNDERLINED = "\u001b[4;31m"
    val GREEN_UNDERLINED = "\u001b[4;32m"
    val YELLOW_UNDERLINED = "\u001b[4;33m"
    val BLUE_UNDERLINED = "\u001b[4;34m"
    val PURPLE_UNDERLINED = "\u001b[4;35m"
    val CYAN_UNDERLINED = "\u001b[4;36m"
    val WHITE_UNDERLINED = "\u001b[4;37m"

    // Background
    val BLACK_BACKGROUND = "\u001b[40m"
    val RED_BACKGROUND = "\u001b[41m"
    val GREEN_BACKGROUND = "\u001b[42m"
    val YELLOW_BACKGROUND = "\u001b[43m"
    val BLUE_BACKGROUND = "\u001b[44m"
    val PURPLE_BACKGROUND = "\u001b[45m"
    val CYAN_BACKGROUND = "\u001b[46m"
    val WHITE_BACKGROUND = "\u001b[47m"

    // High Intensity
    val BLACK_BRIGHT = "\u001b[0;90m"
    val RED_BRIGHT = "\u001b[0;91m"
    val GREEN_BRIGHT = "\u001b[0;92m"
    val YELLOW_BRIGHT = "\u001b[0;93m"
    val BLUE_BRIGHT = "\u001b[0;94m"
    val PURPLE_BRIGHT = "\u001b[0;95m"
    val CYAN_BRIGHT = "\u001b[0;96m"
    val WHITE_BRIGHT = "\u001b[0;97m"

    // Bold High Intensity
    val BLACK_BOLD_BRIGHT = "\u001b[1;90m"
    val RED_BOLD_BRIGHT = "\u001b[1;91m"
    val GREEN_BOLD_BRIGHT = "\u001b[1;92m"
    val YELLOW_BOLD_BRIGHT = "\u001b[1;93m"
    val BLUE_BOLD_BRIGHT = "\u001b[1;94m"
    val PURPLE_BOLD_BRIGHT = "\u001b[1;95m"
    val CYAN_BOLD_BRIGHT = "\u001b[1;96m"
    val WHITE_BOLD_BRIGHT = "\u001b[1;97m"

    // High Intensity backgrounds
    val BLACK_BACKGROUND_BRIGHT = "\u001b[0;100m"
    val RED_BACKGROUND_BRIGHT = "\u001b[0;101m"
    val GREEN_BACKGROUND_BRIGHT = "\u001b[0;102m"
    val YELLOW_BACKGROUND_BRIGHT = "\u001b[0;103m"
    val BLUE_BACKGROUND_BRIGHT = "\u001b[0;104m"
    val PURPLE_BACKGROUND_BRIGHT = "\u001b[0;105m"
    val CYAN_BACKGROUND_BRIGHT = "\u001b[0;106m"
    val WHITE_BACKGROUND_BRIGHT = "\u001b[0;107m"

    def cprintln(color: String, text: String): Unit = println(color + text + RESET)
    def cprint(color: String, text: String): Unit = print(color + text + RESET)
}
