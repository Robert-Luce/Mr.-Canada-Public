package SHA256;
import java.util.ArrayList;

public class Hash {
  private ArrayList<Block> blocks;
  private Word[] messageSchedule;
  private HashValue[] hashValues;

  public Hash(String input) {
    this.hashValues = new HashValue[8];
    for (int i = 0; i < this.hashValues.length; i++) {
      this.hashValues[i] = new HashValue(i);
    }
    Message message = new Message(input);
    this.blocks = message.getBlocks();
    this.messageSchedule = new Word[64];
  }

  public String getHash() {
    HashValue a = this.hashValues[0];
    HashValue b = this.hashValues[1];
    HashValue c = this.hashValues[2];
    HashValue d = this.hashValues[3];
    HashValue e = this.hashValues[4];
    HashValue f = this.hashValues[5];
    HashValue g = this.hashValues[6];
    HashValue h = this.hashValues[7];
    for (Block block : this.blocks) {
      for (int i = 0; i < 16; i++) {
        this.messageSchedule[i] = block.getWord(i);
      }
      for (int i = 16; i < 64; i++) {
        this.messageSchedule[i] = Word.moduloAddition(
            Word.moduloAddition(this.messageSchedule[i - 2].smallSigma1(), this.messageSchedule[i - 7]),
            Word.moduloAddition(this.messageSchedule[i - 15].smallSigma0(), this.messageSchedule[i - 16]));
      }
      for (int i = 0; i < 64; i++) {
        Word T1 = Word.moduloAddition(Word.moduloAddition(Word.moduloAddition(h, Word.Ch(e, f, g)),
            Word.moduloAddition(e.bigSigma1(), this.messageSchedule[i])), new Constant(i));
        Word T2 = Word.moduloAddition(T1.bigSigma0(), Word.Maj(a, b, c));
        h = g;
        g = f;
        f = e;
        HashValue temp = (HashValue) Word.moduloAddition(d, T2);
        e = temp;
        d = c;
        c = b;
        b = a;
        HashValue temp2 = (HashValue) Word.moduloAddition(T1, a);
        a =  temp2;
      }
      this.hashValues[0] = (HashValue) Word.moduloAddition(a, this.hashValues[0]);
      this.hashValues[1] = (HashValue) Word.moduloAddition(b, this.hashValues[1]);
      this.hashValues[2] = (HashValue) Word.moduloAddition(c, this.hashValues[2]);
      this.hashValues[3] = (HashValue) Word.moduloAddition(d, this.hashValues[3]);
      this.hashValues[4] = (HashValue) Word.moduloAddition(e, this.hashValues[4]);
      this.hashValues[5] = (HashValue) Word.moduloAddition(f, this.hashValues[5]);
      this.hashValues[6] = (HashValue) Word.moduloAddition(g, this.hashValues[6]);
      this.hashValues[7] = (HashValue) Word.moduloAddition(h, this.hashValues[7]);
    }
    String output = "";
    for (HashValue hashValue : this.hashValues) {
      output += hashValue.toString();
    }
    return output;
  }
}