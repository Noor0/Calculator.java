package spellcheck;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AutoCorrect{
    private static String word;
    private ArrayList<String> possibleWords = new ArrayList<String>();
    
    public ArrayList<String> checkSpelling(String op){
        word=op.replaceAll("[\\|/.,\\[\\]{}\\+~?><:;_=!#@$%^&\\*()1234567890\\\\]*","").trim().toLowerCase();
        firstCheck();
        return possibleWords;
    }
    
    private  void firstCheck(){
        try {
            boolean firstBool=false;
            Scanner file=new Scanner(new File(getClass().getResource("Dic.txt").getFile()));
            while(file.hasNext()){
                file.next();
                String sh=file.next().toLowerCase().trim();
                if(word.equalsIgnoreCase(sh)){
                    firstBool=true;
                    possibleWords.add("spelling is correct");
                }
            }
            if(!file.hasNext() && firstBool==false){
                skipper();
                doubleFilter();
                possibleProblemSolver();
                //missed(1);
                if(possibleWords.isEmpty())
                    possibleWords.add("it must be some sort of elvish that you're writting!! ._.");
                
            }
        } catch (Exception ex) {
        }
    }
    
    private  String[] possibleProblems(String word){
        String[] array = new String[word.length()-1];
        String s2="";
        for(int i = 0;i<word.length()-1;i++){
            String s1=Character.toString(word.charAt(i+1))+Character.toString(word.charAt(i))+word.substring(i+2,word.length());
            for(int j = 0;j<i;j++)
                s2+=Character.toString(word.charAt(j));
            s1=s2+s1;
            s2="";
            array[i]=s1;
        }
        return array;
    }
    
    private  void possibleProblemSolver() {
        try {
            Scanner file=new Scanner(new File(getClass().getResource("Dic.txt").getFile()));
            String[] possiblities=possibleProblems(word);
            for(int a = 0 ;a<possiblities.length;a++){
                file=new Scanner(new File(getClass().getResource("Dic.txt").getFile()));
                while(file.hasNext()){
                    String sh=file.next().toLowerCase().trim();
                    if(possiblities[a].equalsIgnoreCase(sh)){
                        possibleWords.add(sh);
                    }
                    
                }
                file=null;                
            }
        } catch (Exception ex) {
        }
    }
    
    private  void doubleFilter(){
        try {
            Scanner file=new Scanner(new File(getClass().getResource("Dic.txt").getFile()));
            ArrayList<String> possibilities = new ArrayList<String>();
            for(int a = 0; a < word.length()-1; a++){
                if(word.charAt(a)==word.charAt(a+1)){
                    String s=word.substring(0,a);
                    s+=word.substring(a+1,word.length());
                    possibilities.add(s);
                }
                if(word.charAt(a)==word.charAt(a+1))
                    if(a!=word.length()-2)
                        if(word.charAt(a)==word.charAt(a+1) && word.charAt(a)==word.charAt(a+2)){
                            possibleWords.add("Bro! this is not a joke");
                            System.exit(a);
                        }
            }
            for(int a = 0; a < possibilities.size() ;a++){
                for(int k =0 ;k<possibilities.get(a).length()-1 ;k++)
                    if(possibilities.get(a).charAt(k) == possibilities.get(a).charAt(k+1)){
                        String s=possibilities.get(a).substring(0,k);
                        s+=possibilities.get(a).substring(k+1,possibilities.get(a).length());
                        possibilities.add(s);
                    }
            }
            for(int a = 0 ;a<possibilities.size();a++){
                file=new Scanner(new File(getClass().getResource("Dic.txt").getFile()));
                while(file.hasNext()){
                    String sh=file.next().toLowerCase().trim();
                    if(possibilities.get(a).equalsIgnoreCase(sh)){
                        possibleWords.add(sh);
                    }
                    
                }
                file=null;                
            }
                
        } catch (Exception ex) {
        }
    }
    
    private  void skipper(){
        try {
            int length=word.length(),count=0;
            double algo=Math.ceil(length*.7);
            Scanner file=new Scanner(new File(getClass().getResource("Dic.txt").getFile()));
            
            while(file.hasNext()){
                String matcher=file.next().toLowerCase().trim();
                for(int i = 0 ; i<word.length() ; i++){
                    if(i >= matcher.length())
                        break;
                    else
                        if(word.charAt(i) == matcher.charAt(i))
                            count++;
                    if(count>=algo)
                        if(!(matcher.length()-word.length()>=3))
                            possibleWords.add(matcher);
                }
                count=0;
            }
            
        } catch (Exception ex) {
        }
    }   
    
    private  void missed(){
        try {
            String po=word;
            
            int length=word.length(),count=0,ll=0;
            
            double algo=length-2;
            
            Scanner file=new Scanner(new File(getClass().getResource("Dic.txt").getFile()));
            
            while(file.hasNext()){
                
                String mat=file.next().trim().toLowerCase();
                String s=mat;
                
                if(po.length() < s.length())
                    ll=po.length();
                else
                    ll=s.length();
                
                for (int i = 0; i < ll; i++) {
                    if(i==ll-1)
                        break;
                    if(po.charAt(i)==s.charAt(i)){
                        count++;
                    }
                    else{
                        if(i==0){
                            if(s.length()<po.length())
                                po=po.substring(1);
                            else
                                s=s.substring(1);
                        }
                        else{
                            if(s.length()<po.length())
                                po=po.substring(0,i)+po.substring(i+1);
                            else
                                s=s.substring(0,i)+s.substring(i+1);
                        }
                        ll-=i;
                        i=0;
                        count=0;
                    }
                }
                if(count>=algo)
                    possibleWords.add(mat);
                count=0;
                po=new String(word);
            }
        } catch (Exception ex) {
            
        }
    }

    public void missed(int qwerty){
        try {
            ArrayList<Character> wordlist = new ArrayList<Character>();
            ArrayList<Character> secwordlist = new ArrayList<Character>();
            int length=0;
            int count=0;
            String so,mo;
            Scanner file=new Scanner(new File(getClass().getResource("Dic.txt").getFile()));
            
            for(int o = 0 ; o < word.length() ; o++){
                wordlist.add(word.charAt(o));
            }
            System.out.println(wordlist);
            while(file.hasNext()){
                count=0;
                so=file.next().trim().toLowerCase();

                secwordlist.clear();
                for (int i = 0; i < so.length(); i++) {
                    secwordlist.add(so.charAt(i));
                }
                if(wordlist.size() < secwordlist.size())
                    length=wordlist.size();
                else
                    continue;
                
                for(int y = 0 ; y < length ; y++){
                    if(wordlist.get(y) == secwordlist.get(y))
                        count++;
                    else{
                        secwordlist.remove(y);
                        if(secwordlist.size() < wordlist.size())
                            break;
                        y=0;
                        count=0;
                        
                    }
                }
                
                if(count == so.length()-1);
                    possibleWords.add(so);
                
            }
            
        } catch (Exception ex) {
        }
    }
    
}