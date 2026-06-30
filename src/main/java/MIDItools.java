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

    public static byte[] getHeader(byte speed) {
        return new byte[] {
                0x4D, 0x54, 0x68, 0x64,
                0x00, 0x00, 0x00, 0x06,
                0x00, 0x00, 0x00, 0x01,
                0x00, speed
        };
    }

    public static byte[] addNoteToTrack(byte[] trackdata, byte[] noteEvent) {
        byte[] result = new byte[trackdata.length + noteEvent.length];
        for(int i = 0; i < trackdata.length; i++) {
            result[i] = trackdata[i];
        }
        for(int i = 0; i < noteEvent.length; i++) {
            result[trackdata.length + i] = noteEvent[i];
        }
        return result;
    }

    public static byte[] getTrack(byte instrument, byte[] trackdata) {
        byte length = (byte)(18 + trackdata.length);
        byte[] track = new byte[] {
                0x4D, 0x54, 0x72, 0x6B, 0x00, 0x00, 0x00, length,
                0x00, (byte)0xFF, 0x58, 0x04, 0x04, 0x02, 0x18, 0x08,
                0x00, (byte)0xFF, 0x51, 0x03, 0x07, (byte)0xA1, 0x20,
                0x00, (byte)0xC0, instrument
        };
        track = addNoteToTrack(track, trackdata);
        track = addNoteToTrack(track, new byte[] {(byte)0xFF, 0x2F, 0x00});
        return track;
    }


}
