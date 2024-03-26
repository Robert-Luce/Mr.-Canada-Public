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
        Word temp = Word.moduloAddition(d.getWord(), T2);
        e = new HashValue(temp.getBits());
        d = c;
        c = b;
        b = a;
        Word temp2 = Word.moduloAddition(T1, a.getWord());
        a = new HashValue(temp2.getBits());
      }
      this.hashValues[0] = new HashValue(Word.moduloAddition(a.getWord(), this.hashValues[0].getWord()).getBits());
      this.hashValues[1] = new HashValue(Word.moduloAddition(b.getWord(), this.hashValues[1].getWord()).getBits());
      this.hashValues[2] = new HashValue(Word.moduloAddition(c.getWord(), this.hashValues[2].getWord()).getBits());
      this.hashValues[3] = new HashValue(Word.moduloAddition(d.getWord(), this.hashValues[3].getWord()).getBits());
      this.hashValues[4] = new HashValue(Word.moduloAddition(e.getWord(), this.hashValues[4].getWord()).getBits());
      this.hashValues[5] = new HashValue(Word.moduloAddition(f.getWord(), this.hashValues[5].getWord()).getBits());
      this.hashValues[6] = new HashValue(Word.moduloAddition(g.getWord(), this.hashValues[6].getWord()).getBits());
      this.hashValues[7] = new HashValue(Word.moduloAddition(h.getWord(), this.hashValues[7].getWord()).getBits());
    }
    String output = "";
    for (HashValue hashValue : this.hashValues) {
      output += hashValue.getWord().toString();
    }
    return output;
  }
}