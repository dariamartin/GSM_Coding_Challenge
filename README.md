# GSM_Coding_Challenge
 
Objective 1:
  Provide a mechanism to encode arbitrary standard ASCII text strings
  as DNA. Recall that ASCII characters have numeric values of 0 to 255.
  Examples:
    Input: a, Output: TGAT
    Input: cat, Output: TGACTGATTCTA
    
  Explanation:
    An ASCII character is used as the input. 
    Step 1: Convert the ASCII character from its character form to its numberical equivalent.
      ex. "a" = 97
    Step 2: Convert ASCII number to base 4
      ex. 97 = 1201 
    Step 3: Translate base 4 number to decoded DNA/RNA Sequence
      I created a map to store the decoded values. The map has the following values.
      0=A, 1=T, 2=G, 3=C
      ex. 1201 = TGAT
      
Objective 2: 
  RNA is very similar to DNA, but it replaces T with U. Make your
  program capable of encoding as either DNA or RNA.

  Explanation:
    I use the boolean value convertToDna to tell the encoder if the strand is DNA or RNA.
    I then use the same above method to encode the string. The only change being T is replaced with U.
  
Objective 3: 
  Provide an interface for identifying standard ASCII text substrings
  encoded as DNA; the interface should provide the zero-based index where the
  substring begins or -1 if it does not exist.
  
   Explanation:
    I used Java's indexOf method to return the index. 
  
Objective 4: 
  DNA actually consists of two, complementary strands that are
  attached in such a way that every A matches T and G matches C (and the
  inverses). Given a complementary strand of DNA, output the ASCII equivalent of
  the primary strand.

  Examples:
    Input: ACTGACTAAGAT, Output: cat

  Explanation:
    Step 1: Get the comlementary string
    Step 2: Convert that string to base 4
    Step 3: Convert the base 4 number to an ASCII number
    Step 4: Convert ASCII number to character
    
Objective 5:
  Given two (single) strands of DNA, provide an interface that allows
  finding the longest common subsequence of the two. Recall that common
  subsequences need not be consecutive.

  Explanation:
    I used a Dynamic Programming approach to solve this portion of the challenge.
    I used a table represented as a matrix that represents the row major order of the two strings.
    The longest common substring can then be obtained from this table. 
    
    
    
