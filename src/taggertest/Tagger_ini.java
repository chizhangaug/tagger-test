package taggertest;

import java.io.*;
import java.util.*;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.parser.lexparser.Options;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.models.lexparser.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;


public class Tagger_ini {

	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = null;
		fos = new FileOutputStream("Files/output.txt");
		int lofs = 34;
		
		// TODO Auto-generated method stub
		MaxentTagger tagger = new MaxentTagger(
                "taggers/english-bidirectional-distsim.tagger" );
		
		

		 
		String sample = "Joseph M. Blanchard , 37 , vice president , engineering ; Malcolm A. Hammerton";
		
         //The tagged string
        String tagged = tagger.tagString(sample);
        
        // Output the result
        System.out.println(tagged);
        String[] str = { "-printPCFGkBest", "3"};
      //  String[] str = {};//"-tokenized", 
        //		"-tagSeparator", "_", "-tokenizerFactory ", "edu.stanford.nlp.process.WhitespaceTokenizer", "-tokenizerMethod", "newCoreLabelTokenizerFactory"};
       
        LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz", str);
        lp.setOptionFlags(str);
        
        //PrintStream myout = new PrintStream(new FileOutputStream(new File("Files/output.txt")));      
        
       
        
        
		FileReader inp = new FileReader("Files/input.txt");	
		int c;
		int count = 1;
		while ((c = inp.read()) != -1)
		{
			while (c == '\n') c = inp.read();
			
			String sample1 = "";
			sample1 += (char)c;
			while ((c = inp.read()) != '\n')
			{
				sample1 += (char)c;
			}
			
			if (sample1 == "\n") continue;
			
	        
	 /*       //String[] words = { "This", "is", "an", "easy", "sentence", "." };
	        String[] words = new String[4];
	        String[] tags = new String[4];
	        
	        */
			String[] words = new String[lofs];
	        String[] tags = new String[lofs];
	        String[] parts = sample1.split(" ");
	        int max = parts.length;
	        int j = 0;
	        while (j < max)
	        {
	        	String[] parts1 = parts[j].split("_");
	        	words[j] = parts1[0];
	        	tags[j] = parts1[1];
	        	j++;
	        }
	

	    /* */ //  String[] tags = { "DT", "VBZ", "DT", "JJ", "NN", "." };
	        List<TaggedWord> sentence = new ArrayList<TaggedWord>();
	        assert words.length == tags.length;
	        for (int i = 0; i < words.length; i++) {
	          sentence.add(new TaggedWord(words[i], tags[i]));
	        }
	        Tree parse = lp.apply(sentence); 
	            
	 //       String[] sent = { "This_DT", "is_VBZ", "an_DT", "easy_JJ", "sentence_NN", "._." };
	  //      List<CoreLabel> rawWords = Sentence.toCoreLabelList(words);
	  //      Tree parse = lp.apply(rawWords);
	        
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PrintStream myout = new PrintStream(baos);
	        
	        InputStream in = System.in;
	        PrintStream out = System.out;
	        
	        System.setOut(myout);       
	        System.setErr(myout); 
	        parse.pennPrint();
	        
	        String content = baos.toString();
	        
	        int i = content.indexOf('[');
	        j = content.indexOf(']');
	        
	        String prop = content.substring(i+1, j);
	        
	        System.setOut(out);       
	        System.setErr(out); 
	        
	        byte[] b1 = sample1.getBytes();
	        byte[] b2 = "\n".getBytes();
	        byte[] b3 = prop.getBytes();
	        
	        fos.write(b1);
	        fos.write(b2);
	        fos.write(b3);
	        fos.write(b2);
	        
	        System.out.println(count);
	  //      System.out.println(content);
	        System.out.println(prop);
	        
	        count ++;
		}
        
		fos.close();
        inp.close();
        System.out.println("Finished");
        
      
        Tree tree = lp.parse("Time , the largest newsweekly , had average circulation of below the $ 2.29 billion value United Illuminating places on its bid Rowe also noted that political concerns also worried New England Electric .");  

       
        tree.pennPrint();
  //      TreebankLanguagePack tlp = new PennTreebankLanguagePack();
  //      GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
  //      GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
   //     List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
      //  System.out.println(tdl);
    //    System.out.println();

   //     TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
   //    tp.printTree(parse);
      }
        
 /*       
        Options option1 = new Options();
        
        String[] str = {"-tokenized", "-tagSeparator", "_", "-tokenizerFactory ", "edu.stanford.nlp.process.WhitespaceTokenizer", "-tokenizerMethod", "newCoreLabelTokenizerFactory"};
        
     //   option1.setOptions(str);
        
        LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz", str);
        lp.setOptionFlags(str);
        
        
	}
*/
}
