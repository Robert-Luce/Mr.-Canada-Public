package hashingAlgorithms;

public class Block {
  private Word[] words = new Word[16];
  public Block(int index){
    
  }
  public void addWord(Word word, int index){
    this.words[index] = word;
  }
  public Word getWord(int index){
    return this.words[index];
  }
}