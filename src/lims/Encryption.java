package lims;

/**
 *
 * @author Josh
 */
public class Encryption {
    
    //Perform the rot13 algorithm on 1 char
    public char rot13(char inputChar){
        //Make an array of the alphabet
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        
        //Store the input for manipulation
        char outputChar = inputChar;
        
        //Check if the input character is upper case
        boolean upperCase = !(outputChar == (Character.toLowerCase(outputChar)));
        
        //loop through and check matching, then convert the char
        for(int i = 0; i < 26; i++){
            
            //Convert char to lower case, then check if it matches a spot in the alphabet
            if(Character.toLowerCase(inputChar) == alphabet[i]){
                
                // Use the current position + 13 to get the position of the converted char
                int convertedCharPosition = i + 13;
                
                //Loop the char back into the alphabet (position < 26)
                if(convertedCharPosition >= 26){
                    convertedCharPosition -= 26;
                }
                
                //Convert back to upper case if needed
                if(upperCase){
                    outputChar = Character.toUpperCase(alphabet[convertedCharPosition]);
                }
                else{
                    outputChar = alphabet[convertedCharPosition];
                }
            }
        }
        return outputChar;
    }
    
    //Encryption Method
    public String encrypt(String stringToEncrypt){
        
        //Convert String to a char array for manipulation
        char[] charArray = stringToEncrypt.toCharArray();
        
        //Set up the output string
        String encryptedString = "";
        
        //Encrypt each character of the string
        for(int i = 0; i < charArray.length;i++){
            encryptedString += rot13(charArray[i]);
        }
        return encryptedString;
    }
    
    //Decryption is the same as encryption. This is here for ease of use.
    public String decrypt(String stringToDecrypt){
        return encrypt(stringToDecrypt);
    }
}
