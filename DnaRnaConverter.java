import java.util.*;

class DnaRnaConverter {
    //map that stores DNA bases and their corresonding encoded values
    public Map<Character, Character> dnaMap = Map.of(
        '0', 'A',
        '1', 'T',
        '2', 'G',
        '3', 'C');

    //map that stores RNA bases and their corresonding encoded values
    public Map<Character, Character> rnaMap = Map.of(
        '0', 'A',
        '1', 'U',
        '2', 'G',
        '3', 'C');
    
    //map that stores the complementary dna/rna values
    public Map<Character, Character> complementaryMap = Map.of(
        'T', 'A',
        'A', 'T',
        'C', 'G',
        'G', 'C');

    //Similar to first map, but stores DNA base as key instead
    public Map<Character, Character> charToNumMap = Map.of(
        'A', '0',
        'T', '1',
        'G', '2',
        'C', '3');

    //objective 1 and 2
    public String convertStringToDnaOrRna(String asciiInput, boolean convertToDna)
    {
        StringBuilder encodedStrandStrBldr = new StringBuilder();
        
        //loops through the asciiInput by character
        for(char curChar : asciiInput.toCharArray())
        {
            //convert to base 4
            String asciiBase4Value = convertAsciiToBase4(curChar);

            if(convertToDna) //if it is DNA
            {
                encodedStrandStrBldr.append(convertBase4ToStrand(asciiBase4Value, convertToDna));
            }
            else //if it is RNA
            {
                encodedStrandStrBldr.append(convertBase4ToStrand(asciiBase4Value, false));
            }
        }
        
        return encodedStrandStrBldr.toString();
    }

    //function that converts number from base 10 to base 4
    public String convertAsciiToBase4(int asciiAsInt)
    {
        StringBuilder base4StrBldr = new StringBuilder();

        while(asciiAsInt > 0)
        {
            int base4Value = asciiAsInt % 4;

            base4StrBldr.insert(0, base4Value);

            asciiAsInt /= 4;   
        }

        return base4StrBldr.toString();
    }

    //converts string from base 4 number to decoded DNA or RNA string
    public String convertBase4ToStrand(String base4Input, boolean convertToDna)
    {
        StringBuilder strandStrBldr = new StringBuilder();

        Map<Character, Character> strandMap = (convertToDna) ? dnaMap : rnaMap;

        for(char curChar : base4Input.toCharArray())
        {
            strandStrBldr.append(strandMap.get(curChar));
        }

        return strandStrBldr.toString();
    }

    //objective 3, returns -1 if the substring doesn't exist in the full strand
    public int findStrandSubstringIndex(String fullStrand, String strandSubstring)
    {
        return fullStrand.indexOf(strandSubstring);
    }

    //objective 4
    public String convertComplementStrandToAsciiString(String strand)
    {
        //get complementary strand
        String complementaryStrand = complementStrand(strand);
        //convert that strand to base 4
        String strandAsBase4 = strandToBase4(complementaryStrand);

        //convert to ascii int
        int strandIterator = 0;
        StringBuilder asciiStringBuilder = new StringBuilder();
        while(strandIterator < strandAsBase4.length() - 1)
        {
            String curStrand = strandAsBase4.substring(strandIterator, strandIterator + 4);
            
            int asciiOfCurStrand = convertBase4ToAsciiInteger(curStrand);

            char asciiAsChar = (char) asciiOfCurStrand;

            asciiStringBuilder.append(asciiAsChar);
            strandIterator += 4;
        }

        return asciiStringBuilder.toString();
    }

    //creates complementary string using the complementary map
    public String complementStrand(String strand)
    {
        StringBuilder complementStrBldr = new StringBuilder();
        for(int i = 0; i < strand.length(); i++)
        {
            complementStrBldr.append(complementaryMap.get(strand.charAt(i)));
        }

        return complementStrBldr.toString();
    }

    //converts DNA string to its encoded base 4 number
    public String strandToBase4(String strand)
    {
        StringBuilder base4StrBldr = new StringBuilder();
        for(char curChar : strand.toCharArray())
        {
            base4StrBldr.append(charToNumMap.get(curChar));
        }

        return base4StrBldr.toString();
    }
    //converts base 4 number to the ASCII equivalent
    public int convertBase4ToAsciiInteger(String base4String)
    {
        int exponentValue = 3, iterator = 0, asciiInteger = 0;
        
        while(exponentValue >= 0)
        {
            char curChar = base4String.charAt(iterator);
            int curMultiplier = Integer.parseInt(String.valueOf(curChar));
            
            asciiInteger += Math.pow(4, exponentValue) * curMultiplier;

            exponentValue--;
            iterator++;
        }

        return asciiInteger;
    }

    //objective 5
    //uses a dynamic programming method to find the longest common subsequence 
    public String findLongestCommonSubsequence(String str1, String str2)
    {
        int[][] commonAr = new int[str1.length() + 1][str2.length() + 1];

        for(int i = 1; i < str1.length() + 1; i++)
        {

            for(int j = 1; j < str2.length() + 1; j++)
            {
                if(str1.charAt(i - 1) == str2.charAt(j - 1))
                {
                    commonAr[i][j] = commonAr[i - 1][j - 1] + 1;
                }
                else
                {
                    commonAr[i][j] = Math.max(commonAr[i - 1][j], commonAr[i][j - 1]);
                }

            }
        }

        return getLongestSubsequence(str1, str2, commonAr);
    }


    public String getLongestSubsequence(String str1, String str2, int[][] ar)
    {
        StringBuilder subSeqStrBldr = new StringBuilder();

        for(int str1Iter = str1.length(), str2Iter = str2.length(); str1Iter != 0 && str2Iter != 0; )
        {
            if(ar[str1Iter][str2Iter] == ar[str1Iter - 1][str2Iter])
            {
                str1Iter--;
            }
            else if(ar[str1Iter][str2Iter] == ar[str1Iter][str2Iter - 1])
            {
                str2Iter--;
            }
            else
            {
                subSeqStrBldr.append(str1.charAt(str1Iter - 1));
                str1Iter--;
                str2Iter--;
            }
        }


        return subSeqStrBldr.reverse().toString();
    }

    public static void main(String[] args) {
        
        DnaRnaConverter DnaRnaObj = new DnaRnaConverter();
        System.out.println(DnaRnaObj.convertStringToDnaOrRna("cat", true));
        System.out.println(DnaRnaObj.convertStringToDnaOrRna("cat", false));
        System.out.println(DnaRnaObj.findStrandSubstringIndex("TGACTGATTCTA", "TGAT"));

        System.out.println(DnaRnaObj.convertComplementStrandToAsciiString("ACTGACTAAGAT"));

        System.out.println(DnaRnaObj.findLongestCommonSubsequence("TGACTGATTCTA", "ATTGAT"));
    }

}