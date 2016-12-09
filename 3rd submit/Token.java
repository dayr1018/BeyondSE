class Token{

   String style;
   String string;

   Token(String str)
   {
      string = str;
   };

   //method
   void check()
   {
      if(string.indexOf("**")!=-1)
      {
         string = string.replaceAll("**", "");
         style = "bold";
      }
   }
}