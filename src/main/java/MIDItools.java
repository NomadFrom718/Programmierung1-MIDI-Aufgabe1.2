public class MIDItools {
    public static byte getNote(char note, int octave, boolean sharp) {
        byte noteval = 0;
        if(octave < 0 || octave > 10) {
            return 0;
        }
        switch(note) {
            case 'C':
                break;
            case 'D':
                noteval = 2;
                break;
            case 'E':
                noteval = 4;
                break;
            case 'F':
                noteval = 5;
                break;
            case 'G':
                noteval = 7;
                break;
            case 'A':
                noteval = 9;
                break;
            case 'B':
                noteval = 11;
                break;
            default:
                return 0;
        }
        if(sharp) {
            noteval += 1;
        }
        noteval += (octave+1)*12;
        if(noteval > 127) {
            return 0;
        }
        return noteval;
    }

    public static byte[] getNoteEvent(byte delay, boolean noteOn, byte note, byte velocity) {
        return new byte[] {
                delay, noteOn?(byte)0b10010000:(byte)0b10000000,
                note, velocity
        };
    }
}
