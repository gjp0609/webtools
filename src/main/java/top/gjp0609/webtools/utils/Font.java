package top.gjp0609.webtools.utils;

public interface Font {

    public enum Type {
        /**
         * Font Type: <b>Bold</b>
         */
        BOLD(1),

        /**
         * Font Type: <u>Underline</u>
         */
        UNDERLINE(4),

        /**
         * Font Type: <span style="background-color:#000000;color:#FFFFFF;">Invert</span>
         */
        INVERT(7);

        private int value;

        Type(int value) {
            this.value = value;
        }

        public int getTypeValue() {
            return value;
        }
    }


    enum Color {
        /**
         * Font Color: <span color='#FFFFFF'>Black #FFFFFF</span>
         */
        BLACK(30),

        /**
         * Font Color: <span color='#CD0000'>Dark Red #CD0000</span>
         */
        DARK_RED(31),

        /**
         * Font Color: <span color='#00CD00'>Dark Green #CD0000</span>
         */
        DARK_GREEN(32),

        /**
         * Font Color: <span color='#C4A000'>Dark Yellow #C4A000</span>
         */
        DARK_YELLOW(33),

        /**
         * Font Color: <span color='#0000EE'>Dark Blue #0000EE</span>
         */
        DARK_BLUE(34),

        /**
         * Font Color: <span color='#CD00CD'>Light Purple #CD00CD</span>
         */
        LIGHT_PURPLE(35),

        /**
         * Font Color: <span color='#00CCCC'>Dark Cyan #00CCCC</span>
         */
        DARK_CYAN(36),

        /**
         * Font Color: <span color='#AAAAAA'>Light Gray #AAAAA</span>
         */
        LIGHT_GRAY(37),

        /**
         * Font Color: <span color='#555555'>Dark Gray #555555</span>
         */
        DARK_GRAY(90),

        /**
         * Font Color: <span color='#FF0000'>Red #FF0000</span>
         */
        RED(91),

        /**
         * Font Color: <span color='#00FF00'>Green #00FF00</span>
         */
        GREEN(92),

        /**
         * Font Color: <span color='#EAEA00'>Yellow #EAEA00</span>
         */
        YELLOW(93),

        /**
         * Font Color: <span color='#5C5CFF'>Light Blue #5C5CFF</span>
         */
        LIGHT_BLUE(94),

        /**
         * Font Color: <span color='#5C5CFF'>Magenta #5C5CFF</span>
         */
        MAGENTA(95),

        /**
         * Font Color: <span color='#00FFFF'>Cyan #00FFFF</span>
         */
        CYAN(96),

        /**
         * Font Color: <span color='#000000'>Light Gray #000000</span>
         */
        WHITE(97);

        private int value;

        Color(int value) {
            this.value = value;
        }

        public int getColorValue() {
            return value;
        }

        public int getBackgroundColorValue() {
            return value + 10;
        }
    }
}


