public class PackCaesar {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public String newEncrypt(boolean type, String inputString, int shiftKey) {
        if (type) {
            return packing(inputString, shiftKey);
        } else {
            return unpacking(inputString, shiftKey);
        }
    }

    public static String packing(String inputStr, int shiftKey) {
        // convert inputStr into lower case
        inputStr = inputStr.toLowerCase();

        // encryptStr to store encrypted data
        StringBuilder encryptStr = new StringBuilder();

        // use for loop for traversing each character of the input string
        for (int i = 0; i < inputStr.length(); i++) {
            // get position of each character of inputStr in ALPHABET
            int pos = ALPHABET.indexOf(inputStr.charAt(i));

            // get encrypted char for each char of inputStr
            int encryptPos = (shiftKey + pos) % 26;
            char encryptChar = ALPHABET.charAt(encryptPos);

            // add encrypted char to encrypted string
            encryptStr.append(encryptChar);
        }

        // return encrypted string
        return encryptStr.toString();
    }

    public static String unpacking(String inputStr, int shiftKey)
    {
        // convert inputStr into lower case
        inputStr = inputStr.toLowerCase();

        // decryptStr to store decrypted data
        StringBuilder decryptStr = new StringBuilder();

        // use for loop for traversing each character of the input string
        for (int i = 0; i < inputStr.length(); i++)
        {

            // get position of each character of inputStr in ALPHABET
            int pos = ALPHABET.indexOf(inputStr.charAt(i));

            // get decrypted char for each char of inputStr
            int decryptPos = (pos - shiftKey) % 26;

            // if decryptPos is negative
            if (decryptPos < 0){
                decryptPos = ALPHABET.length() + decryptPos;
            }
            char decryptChar = ALPHABET.charAt(decryptPos);

            // add decrypted char to decrypted string
            decryptStr.append(decryptChar);
        }
        // return decrypted string
        return decryptStr.toString();
    }

}

