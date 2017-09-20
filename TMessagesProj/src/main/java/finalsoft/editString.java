package finalsoft;

import org.telegram.messenger.exoplayer2.text.ttml.TtmlNode;

import java.util.ArrayList;
import java.util.Iterator;


public class editString {

    String other_ki;
    String other_km;
    ArrayList<String> list;
    ArrayList<String> list2;


    public String getNewString(String str, int i) {
        list = new ArrayList();
        list2 = new ArrayList();
        Splator(str);

        int k = 0;

        list.clear();

        switch (i) {
            case 0 :
                return str;

            case 1:
                str = Change_name1(str);
                break;
            case 2:
                str = Change_name2(str);
//                arraysplator2(list2);
                break;
            case 3:
                str = Change_name9(str);
//                str = Change_name3(str);
//
//                arraysplator3(list2);
                break;
            case 4:
                str = Change_name4(str);
//                arraysplator4(list2);
                break;
            case 5:

                ////
//                str = str.replaceAll("ک","ڪ");
//                str = str.replaceAll("د","נ");
//                str = str.replaceAll("و","ۅ");
//                str = str.replaceAll("ی","ے");
//                str = str.replaceAll("ع","؏");
//                str = str.replaceAll("غ","ۼ");
//                return str;
                str = Change_name5(str);
                break;
            case 6:
                str = Change_name10(str);
//                str = Change_name6(str);
//                arraysplator6(list2);
                break;
            case 7:
                str = Change_name8(str);
//                str = Change_name7(str);
//                arraysplator7(list2);
                break;
            case 8:
                str = Change_name8(str);
//                arraysplator7(list2);
                break;
            case 9:
                str = Change_name9(str);
//                arraysplator9(list2);
                break;
            case 10:
                str = Change_name10(str);
//                arraysplator9(list2);
                break;


        }

        String listString = "";
        Iterator it = list.iterator();
        while (it.hasNext()) {
            listString = listString + ((String) it.next());
        }

//
//        if (listString.length() > str.length()) {
//            listString = listString.substring(str.length());
//
//            listString = listString.substring(listString.length() - k -1);
//        }

        return listString;

    }

    public String Splator(String splator) {
        String[] indexm = splator.split("SAF284619826481264FLGE");
        for (String str : indexm) {
            this.other_km = str;
            this.list2.add(this.other_km);
        }
        return splator;
    }



    public  String Change_name1(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("q") || other_ki.equals("Q")) {
                    list.add("\u01aa");
                }
                if (other_ki.equals("w") || other_ki.equals("W")) {
                    list.add("\u2c73");
                }
                if (other_ki.equals("e") || other_ki.equals("E")) {
                    list.add("\u2c78");
                }
                if (other_ki.equals("r") || other_ki.equals("R")) {
                    list.add("\u0213");
                }
                if (other_ki.equals("t") || other_ki.equals("T")) {
                    list.add("\u01ab");
                }
                if (other_ki.equals("y") || other_ki.equals("Y")) {
                    list.add("\u01b4");
                }
                if (other_ki.equals("u") || other_ki.equals("U")) {
                    list.add("\u0215");
                }
                if (other_ki.equals("i") || other_ki.equals("I")) {
                    list.add("\u00a1");
                }
                if (other_ki.equals("o") || other_ki.equals("O")) {
                    list.add("\u2c7a");
                }
                if (other_ki.equals(TtmlNode.TAG_P) || other_ki.equals("P")) {
                    list.add("\u01f7");
                }
                if (other_ki.equals("a") || other_ki.equals("A")) {
                    list.add("\u00e1");
                }
                if (other_ki.equals("s") || other_ki.equals("S")) {
                    list.add("\u015f");
                }
                if (other_ki.equals("d") || other_ki.equals("D")) {
                    list.add("\u018c");
                }
                if (other_ki.equals("f") || other_ki.equals("F")) {
                    list.add("\u0192");
                }
                if (other_ki.equals("g") || other_ki.equals("G")) {
                    list.add("\u011d");
                }
                if (other_ki.equals("h") || other_ki.equals("H")) {
                    list.add("\u2c68");
                }
                if (other_ki.equals("j") || other_ki.equals("J")) {
                    list.add("j");
                }
                if (other_ki.equals("k") || other_ki.equals("K")) {
                    list.add("\u0199");
                }
                if (other_ki.equals("l") || other_ki.equals("L")) {
                    list.add("\u2c61");
                }
                if (other_ki.equals("z") || other_ki.equals("Z")) {
                    list.add("\u01b6");
                }
                if (other_ki.equals("x") || other_ki.equals("X")) {
                    list.add("\u00d7");
                }
                if (other_ki.equals("c") || other_ki.equals("C")) {
                    list.add("\u0188");
                }
                if (other_ki.equals("v") || other_ki.equals("V")) {
                    list.add("\u2c74");
                }
                if (other_ki.equals("b") || other_ki.equals("B")) {
                    list.add("\u00fe");
                }
                if (other_ki.equals("n") || other_ki.equals("N")) {
                    list.add("\u019e");
                }
                if (other_ki.equals("m") || other_ki.equals("M")) {
                    list.add("\u1e43");
                }
                if (other_ki.equals("?")) {
                    list.add("?");
                }
                if (other_ki.equals("\u061f")) {
                    list.add("\u061f");
                }
                if (other_ki.equals("\u0635")) {
                    list.add("\u0635");
                }
                if (other_ki.equals(".")) {
                    list.add(".");
                }
                if (other_ki.equals("!")) {
                    list.add("!");
                }
                if (other_ki.equals("@")) {
                    list.add("@");
                }
                if (other_ki.equals("#")) {
                    list.add("#");
                }
                if (other_ki.equals("$")) {
                    list.add("$");
                }
                if (other_ki.equals("%")) {
                    list.add("%");
                }
                if (other_ki.equals("^")) {
                    list.add("^");
                }
                if (other_ki.equals("&")) {
                    list.add("&");
                }
                if (other_ki.equals(")")) {
                    list.add(")");
                }
                if (other_ki.equals("(")) {
                    list.add("(");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("_")) {
                    list.add("_");
                }
                if (other_ki.equals("=")) {
                    list.add("=");
                }
                if (other_ki.equals("+")) {
                    list.add("+");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("*")) {
                    list.add("*");
                }
                if (other_ki.equals("/")) {
                    list.add("/");
                }
                if (other_ki.equals("\u060c")) {
                    list.add("\u060c");
                }
                if (other_ki.equals("\u2019")) {
                    list.add("\u2019");
                }
                if (other_ki.equals(":")) {
                    list.add(":");
                }
                if (other_ki.equals("<")) {
                    list.add("<");
                }
                if (other_ki.equals(">")) {
                    list.add(">");
                }
                if (other_ki.equals("\u061b")) {
                    list.add("\u061b");
                }
                if (other_ki.equals("\u00a9")) {
                    list.add("\u00a9");
                }
                if (other_ki.equals("}")) {
                    list.add("}");
                }
                if (other_ki.equals("{")) {
                    list.add("{");
                }
                if (other_ki.equals("[")) {
                    list.add("[");
                }
                if (other_ki.equals("]")) {
                    list.add("]");
                }
                if (other_ki.equals("\u00ab")) {
                    list.add("\u00ab");
                }
                if (other_ki.equals("\u00bb")) {
                    list.add("\u00bb");
                }
                if (other_ki.equals("\u06f1")) {
                    list.add("\u06f1");
                }
                if (other_ki.equals("\u06f2")) {
                    list.add("\u06f2");
                }
                if (other_ki.equals("\u06f3")) {
                    list.add("\u06f3");
                }
                if (other_ki.equals("\u06f4")) {
                    list.add("\u06f4");
                }
                if (other_ki.equals("\u06f5")) {
                    list.add("\u06f5");
                }
                if (other_ki.equals("\u06f7")) {
                    list.add("\u06f7");
                }
                if (other_ki.equals("\u06f6")) {
                    list.add("\u06f6");
                }
                if (other_ki.equals("\u06f8")) {
                    list.add("\u06f8");
                }
                if (other_ki.equals("\u06f9")) {
                    list.add("\u06f9");
                }
                if (other_ki.equals("\u06f0")) {
                    list.add("\u06f0");
                }
                if (other_ki.equals("1")) {
                    list.add("1");
                }
                if (other_ki.equals("2")) {
                    list.add("2");
                }
                if (other_ki.equals("3")) {
                    list.add("3");
                }
                if (other_ki.equals("4")) {
                    list.add("4");
                }
                if (other_ki.equals("5")) {
                    list.add("5");
                }
                if (other_ki.equals("6")) {
                    list.add("6");
                }
                if (other_ki.equals("7")) {
                    list.add("7");
                }
                if (other_ki.equals("8")) {
                    list.add("8");
                }
                if (other_ki.equals("9")) {
                    list.add("9");
                }
                if (other_ki.equals("0")) {
                    list.add("0");
                }
                if (other_ki.equals("\u0647")) {
                    list.add("\ufba9");
                }
                if (other_ki.equals("\u06cc")) {
                    list.add("\u0640\u0640\u0640\u06d2\u0650\u0650");
                }
                if (other_ki.equals("\u0644")) {
                    list.add("\u06b6");
                }
                if (other_ki.equals("\u0622")) {
                    list.add("\u0622");
                }
                if (other_ki.equals("\u0627")) {
                    list.add("\u0627");
                }
                if (other_ki.equals("\u0623")) {
                    list.add("\u0623");
                }
                if (other_ki.equals("\u0625")) {
                    list.add("\u0625");
                }
                if (other_ki.equals("\u0636")) {
                    list.add("\u06fb");
                }
                if (other_ki.equals("\u062b")) {
                    list.add("\u062b");
                }
                if (other_ki.equals("\u0630")) {
                    list.add("\u0630");
                }
                if (other_ki.equals("\u0632")) {
                    list.add("\u0632");
                }
                if (other_ki.equals("\u0698")) {
                    list.add("\u076b");
                }
                if (other_ki.equals("\u0642")) {
                    list.add("\u06a8");
                }
                if (other_ki.equals("\u0641")) {
                    list.add("\u06a6");
                }
                if (other_ki.equals("\u063a")) {
                    list.add("\u06a0");
                }
                if (other_ki.equals("\u0639")) {
                    list.add("\u0639");
                }
                if (other_ki.equals("\u062e")) {
                    list.add("\u0682");
                }
                if (other_ki.equals("\u062c")) {
                    list.add("\u0684");
                }
                if (other_ki.equals("\u0686")) {
                    list.add("\u0758");
                }
                if (other_ki.equals("\u062f")) {
                    list.add("\u062f");
                }
                if (other_ki.equals("\u0628")) {
                    list.add("\u0755");
                }
                if (other_ki.equals("\u0633")) {
                    list.add("\u076d");
                }
                if (other_ki.equals("\u06a9")) {
                    list.add("\u06ad");
                }
                if (other_ki.equals("\u06af")) {
                    list.add("\u06b4");
                }
                if (other_ki.equals("\u067e")) {
                    list.add("\u0680");
                }
                if (other_ki.equals("\u0631")) {
                    list.add("\u0696");
                }
                if (other_ki.equals("\u0648")) {
                    list.add("\u06cf");
                }
                if (other_ki.equals("\u0645")) {
                    list.add("\u0766");
                }
                if (other_ki.equals("\u062a")) {
                    list.add("\u0753");
                }
                if (other_ki.equals("\u0634")) {
                    list.add("\u06fa");
                }
                if (other_ki.equals("\u0646")) {
                    list.add("\u06ba");
                }
                if (other_ki.equals("\u0637")) {
                    list.add("\u0637");
                }
                if (other_ki.equals("\u062d")) {
                    list.add("ح");
                }
                if (other_ki.equals("\u0638")) {
                    list.add("ظ");
                }
                if (other_ki.equals(" ")) {
                    list.add(" ");
                }
                if (other_ki.equals("\u0635")) {
                    list.add("\u0635");
                }
            } else if (i <= index.length) {
                if (other_ki.equals("q") || other_ki.equals("Q")) {
                    list.add("\u01aa");
                }
                if (other_ki.equals("w") || other_ki.equals("W")) {
                    list.add("\u2c73");
                }
                if (other_ki.equals("e") || other_ki.equals("E")) {
                    list.add("\u2c78");
                }
                if (other_ki.equals("r") || other_ki.equals("R")) {
                    list.add("\u0213");
                }
                if (other_ki.equals("t") || other_ki.equals("T")) {
                    list.add("\u01ab");
                }
                if (other_ki.equals("y") || other_ki.equals("Y")) {
                    list.add("\u01b4");
                }
                if (other_ki.equals("u") || other_ki.equals("U")) {
                    list.add("\u0215");
                }
                if (other_ki.equals("i") || other_ki.equals("I")) {
                    list.add("\u00a1");
                }
                if (other_ki.equals("o") || other_ki.equals("O")) {
                    list.add("\u2c7a");
                }
                if (other_ki.equals(TtmlNode.TAG_P) || other_ki.equals("P")) {
                    list.add("\u01f7");
                }
                if (other_ki.equals("a") || other_ki.equals("A")) {
                    list.add("\u00e1");
                }
                if (other_ki.equals("s") || other_ki.equals("S")) {
                    list.add("\u015f");
                }
                if (other_ki.equals("d") || other_ki.equals("D")) {
                    list.add("\u018c");
                }
                if (other_ki.equals("f") || other_ki.equals("F")) {
                    list.add("\u0192");
                }
                if (other_ki.equals("g") || other_ki.equals("G")) {
                    list.add("\u011d");
                }
                if (other_ki.equals("h") || other_ki.equals("H")) {
                    list.add("\u2c68");
                }
                if (other_ki.equals("j") || other_ki.equals("J")) {
                    list.add("j");
                }
                if (other_ki.equals("k") || other_ki.equals("K")) {
                    list.add("\u0199");
                }
                if (other_ki.equals("l") || other_ki.equals("L")) {
                    list.add("\u2c61");
                }
                if (other_ki.equals("z") || other_ki.equals("Z")) {
                    list.add("\u01b6");
                }
                if (other_ki.equals("x") || other_ki.equals("X")) {
                    list.add("\u00d7");
                }
                if (other_ki.equals("c") || other_ki.equals("C")) {
                    list.add("\u0188");
                }
                if (other_ki.equals("v") || other_ki.equals("V")) {
                    list.add("\u2c74");
                }
                if (other_ki.equals("b") || other_ki.equals("B")) {
                    list.add("\u00fe");
                }
                if (other_ki.equals("n") || other_ki.equals("N")) {
                    list.add("\u019e");
                }
                if (other_ki.equals("m") || other_ki.equals("M")) {
                    list.add("\u1e43");
                }
                if (other_ki.equals("\u0635")) {
                    list.add("صـ");
                }
                if (other_ki.equals("?")) {
                    list.add("?");
                }
                if (other_ki.equals("\u061f")) {
                    list.add("؟");
                }
                if (other_ki.equals(".")) {
                    list.add(".");
                }
                if (other_ki.equals("!")) {
                    list.add("!");
                }
                if (other_ki.equals("@")) {
                    list.add("@");
                }
                if (other_ki.equals("#")) {
                    list.add("#");
                }
                if (other_ki.equals("$")) {
                    list.add("$");
                }
                if (other_ki.equals("%")) {
                    list.add("%");
                }
                if (other_ki.equals("^")) {
                    list.add("^");
                }
                if (other_ki.equals("&")) {
                    list.add("&");
                }
                if (other_ki.equals(")")) {
                    list.add(")");
                }
                if (other_ki.equals("(")) {
                    list.add("(");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("_")) {
                    list.add("_");
                }
                if (other_ki.equals("=")) {
                    list.add("=");
                }
                if (other_ki.equals("+")) {
                    list.add("+");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("*")) {
                    list.add("*");
                }
                if (other_ki.equals("/")) {
                    list.add("/");
                }
                if (other_ki.equals("\u060c")) {
                    list.add("،");
                }
                if (other_ki.equals("\u2019")) {
                    list.add("’");
                }
                if (other_ki.equals(":")) {
                    list.add(":");
                }
                if (other_ki.equals("<")) {
                    list.add("<");
                }
                if (other_ki.equals(">")) {
                    list.add(">");
                }
                if (other_ki.equals("\u061b")) {
                    list.add("؛");
                }
                if (other_ki.equals("\u00a9")) {
                    list.add("©");
                }
                if (other_ki.equals("}")) {
                    list.add("}");
                }
                if (other_ki.equals("{")) {
                    list.add("{");
                }
                if (other_ki.equals("[")) {
                    list.add("[");
                }
                if (other_ki.equals("]")) {
                    list.add("]");
                }
                if (other_ki.equals("\u00ab")) {
                    list.add("«");
                }
                if (other_ki.equals("\u00bb")) {
                    list.add("»");
                }
                if (other_ki.equals("\u06f1")) {
                    list.add("۱");
                }
                if (other_ki.equals("\u06f2")) {
                    list.add("۲");
                }
                if (other_ki.equals("\u06f3")) {
                    list.add("۳");
                }
                if (other_ki.equals("\u06f4")) {
                    list.add("۴");
                }
                if (other_ki.equals("\u06f5")) {
                    list.add("\u06f5");
                }
                if (other_ki.equals("\u06f7")) {
                    list.add("\u06f7");
                }
                if (other_ki.equals("\u06f6")) {
                    list.add("\u06f6");
                }
                if (other_ki.equals("\u06f8")) {
                    list.add("\u06f8");
                }
                if (other_ki.equals("\u06f9")) {
                    list.add("۹");
                }
                if (other_ki.equals("\u06f0")) {
                    list.add("۰");
                }
                if (other_ki.equals("1")) {
                    list.add("1");
                }
                if (other_ki.equals("2")) {
                    list.add("2");
                }
                if (other_ki.equals("3")) {
                    list.add("3");
                }
                if (other_ki.equals("4")) {
                    list.add("4");
                }
                if (other_ki.equals("5")) {
                    list.add("5");
                }
                if (other_ki.equals("6")) {
                    list.add("6");
                }
                if (other_ki.equals("7")) {
                    list.add("7");
                }
                if (other_ki.equals("8")) {
                    list.add("8");
                }
                if (other_ki.equals("9")) {
                    list.add("9");
                }
                if (other_ki.equals("0")) {
                    list.add("0");
                }
                if (other_ki.equals("\u0622")) {
                    list.add("آ");
                }
                if (other_ki.equals("\u0627")) {
                    list.add("ا");
                }
                if (other_ki.equals("\u0623")) {
                    list.add("أ");
                }
                if (other_ki.equals("\u0625")) {
                    list.add("إ");
                }
                if (other_ki.equals("\u0636")) {
                    list.add("ضـ");
                }
                if (other_ki.equals("\u062b")) {
                    list.add("ثــ");
                }
                if (other_ki.equals("\u0630")) {
                    list.add("ذ");
                }
                if (other_ki.equals("\u0632")) {
                    list.add("ز");
                }
                if (other_ki.equals("\u0698")) {
                    list.add("ژ");
                }
                if (other_ki.equals("\u0642")) {
                    list.add("قــ");
                }
                if (other_ki.equals("\u0641")) {
                    list.add("فـ");
                }
                if (other_ki.equals("\u063a")) {
                    list.add("غـ");
                }
                if (other_ki.equals("\u0639")) {
                    list.add("عـ");
                }
                if (other_ki.equals("\u062e")) {
                    list.add("خـــ");
                }
                if (other_ki.equals("\u062c")) {
                    list.add("جـ");
                }
                if (other_ki.equals("\u0686")) {
                    list.add("چ");
                }
                if (other_ki.equals("\u062f")) {
                    list.add("د");
                }
                if (other_ki.equals("\u0628")) {
                    list.add("بـــ");
                }
                if (other_ki.equals("\u0644")) {
                    list.add("ل");
                }
                if (other_ki.equals("\u06cc")) {
                    list.add("ی");
                }
                if (other_ki.equals("\u0633")) {
                    list.add("ســــ");
                }
                if (other_ki.equals("\u06a9")) {
                    list.add("کـ");
                }
                if (other_ki.equals("\u06af")) {
                    list.add("گـ");
                }
                if (other_ki.equals("\u067e")) {
                    list.add("پــ");
                }
                if (other_ki.equals("\u0631")) {
                    list.add("ر");
                }
                if (other_ki.equals("\u0648")) {
                    list.add("و");
                }
                if (other_ki.equals("\u0645")) {
                    list.add("مــ");
                }
                if (other_ki.equals("\u062a")) {
                    list.add("تــ");
                }
                if (other_ki.equals("\u0647")) {
                    list.add("ه");
                }
                if (other_ki.equals("\u0634")) {
                    list.add("ش");
                }
                if (other_ki.equals("\u0646")) {
                    list.add("نـ");
                }
                if (other_ki.equals("\u0637")) {
                    list.add("طـ");
                }
                if (other_ki.equals("\u062d")) {
                    list.add("حـــ");
                }
                if (other_ki.equals("\u0638")) {
                    list.add("ظـ");
                }
                if (other_ki.equals(" ")) {
                    list.add(" ");
                }
            }
        }
        return change_en;
    }



    public  String Change_name2(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 1; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("q") || other_ki.equals("Q")) {
                    list.add("Q");
                }
                if (other_ki.equals("w") || other_ki.equals("W")) {
                    list.add("\u2c72");
                }
                if (other_ki.equals("e") || other_ki.equals("E")) {
                    list.add("\u018e");
                }
                if (other_ki.equals("r") || other_ki.equals("R")) {
                    list.add("\u0212");
                }
                if (other_ki.equals("t") || other_ki.equals("T")) {
                    list.add("\u01ae");
                }
                if (other_ki.equals("y") || other_ki.equals("Y")) {
                    list.add("\u01b3");
                }
                if (other_ki.equals("u") || other_ki.equals("U")) {
                    list.add("\u01af");
                }
                if (other_ki.equals("i") || other_ki.equals("I")) {
                    list.add("\u0197");
                }
                if (other_ki.equals("o") || other_ki.equals("O")) {
                    list.add("\u019f");
                }
                if (other_ki.equals(TtmlNode.TAG_P) || other_ki.equals("P")) {
                    list.add("\u1e56");
                }
                if (other_ki.equals("a") || other_ki.equals("A")) {
                    list.add("\u0104");
                }
                if (other_ki.equals("s") || other_ki.equals("S")) {
                    list.add("\u2c7e");
                }
                if (other_ki.equals("d") || other_ki.equals("D")) {
                    list.add("\u0189");
                }
                if (other_ki.equals("f") || other_ki.equals("F")) {
                    list.add("\u0191");
                }
                if (other_ki.equals("g") || other_ki.equals("G")) {
                    list.add("\u01e6");
                }
                if (other_ki.equals("h") || other_ki.equals("H")) {
                    list.add("\u0126");
                }
                if (other_ki.equals("j") || other_ki.equals("J")) {
                    list.add("J");
                }
                if (other_ki.equals("k") || other_ki.equals("K")) {
                    list.add("\u2c69");
                }
                if (other_ki.equals("l") || other_ki.equals("L")) {
                    list.add("\u2c62");
                }
                if (other_ki.equals("z") || other_ki.equals("Z")) {
                    list.add("\u2c7f");
                }
                if (other_ki.equals("x") || other_ki.equals("X")) {
                    list.add("\u1e8a");
                }
                if (other_ki.equals("c") || other_ki.equals("C")) {
                    list.add("\u0187");
                }
                if (other_ki.equals("v") || other_ki.equals("V")) {
                    list.add("\u2c71");
                }
                if (other_ki.equals("b") || other_ki.equals("B")) {
                    list.add("\u0181");
                }
                if (other_ki.equals("n") || other_ki.equals("N")) {
                    list.add("\u019d");
                }
                if (other_ki.equals("m") || other_ki.equals("M")) {
                    list.add("\u1e42");
                }
                if (other_ki.equals("?")) {
                    list.add("?");
                }
                if (other_ki.equals("\u0635")) {
                    list.add("\u0635");
                }
                if (other_ki.equals("\u061f")) {
                    list.add("\u061f");
                }
                if (other_ki.equals(".")) {
                    list.add(".");
                }
                if (other_ki.equals("!")) {
                    list.add("!");
                }
                if (other_ki.equals("@")) {
                    list.add("@");
                }
                if (other_ki.equals("#")) {
                    list.add("#");
                }
                if (other_ki.equals("$")) {
                    list.add("$");
                }
                if (other_ki.equals("%")) {
                    list.add("%");
                }
                if (other_ki.equals("^")) {
                    list.add("^");
                }
                if (other_ki.equals("&")) {
                    list.add("&");
                }
                if (other_ki.equals(")")) {
                    list.add(")");
                }
                if (other_ki.equals("(")) {
                    list.add("(");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("_")) {
                    list.add("_");
                }
                if (other_ki.equals("=")) {
                    list.add("=");
                }
                if (other_ki.equals("+")) {
                    list.add("+");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("*")) {
                    list.add("*");
                }
                if (other_ki.equals("/")) {
                    list.add("/");
                }
                if (other_ki.equals("\u060c")) {
                    list.add("\u060c");
                }
                if (other_ki.equals("\u2019")) {
                    list.add("\u2019");
                }
                if (other_ki.equals(":")) {
                    list.add(":");
                }
                if (other_ki.equals("<")) {
                    list.add("<");
                }
                if (other_ki.equals(">")) {
                    list.add(">");
                }
                if (other_ki.equals("\u061b")) {
                    list.add("\u061b");
                }
                if (other_ki.equals("\u00a9")) {
                    list.add("\u00a9");
                }
                if (other_ki.equals("}")) {
                    list.add("}");
                }
                if (other_ki.equals("{")) {
                    list.add("{");
                }
                if (other_ki.equals("[")) {
                    list.add("[");
                }
                if (other_ki.equals("]")) {
                    list.add("]");
                }
                if (other_ki.equals("\u00ab")) {
                    list.add("\u00ab");
                }
                if (other_ki.equals("\u00bb")) {
                    list.add("\u00bb");
                }
                if (other_ki.equals("\u06f1")) {
                    list.add("\u06f1");
                }
                if (other_ki.equals("\u06f2")) {
                    list.add("\u06f2");
                }
                if (other_ki.equals("\u06f3")) {
                    list.add("\u06f3");
                }
                if (other_ki.equals("\u06f4")) {
                    list.add("\u06f4");
                }
                if (other_ki.equals("\u06f5")) {
                    list.add("\u06f5");
                }
                if (other_ki.equals("\u06f7")) {
                    list.add("\u06f7");
                }
                if (other_ki.equals("\u06f6")) {
                    list.add("\u06f6");
                }
                if (other_ki.equals("\u06f8")) {
                    list.add("\u06f8");
                }
                if (other_ki.equals("\u06f9")) {
                    list.add("\u06f9");
                }
                if (other_ki.equals("\u06f0")) {
                    list.add("\u06f0");
                }
                if (other_ki.equals("1")) {
                    list.add("1");
                }
                if (other_ki.equals("2")) {
                    list.add("2");
                }
                if (other_ki.equals("3")) {
                    list.add("3");
                }
                if (other_ki.equals("4")) {
                    list.add("4");
                }
                if (other_ki.equals("5")) {
                    list.add("5");
                }
                if (other_ki.equals("6")) {
                    list.add("6");
                }
                if (other_ki.equals("7")) {
                    list.add("7");
                }
                if (other_ki.equals("8")) {
                    list.add("8");
                }
                if (other_ki.equals("9")) {
                    list.add("9");
                }
                if (other_ki.equals("0")) {
                    list.add("0");
                }
                if (other_ki.equals("\u0647")) {
                    list.add("\u0640\u0640\ufba9");
                }
                if (other_ki.equals("\u06cc")) {
                    list.add("\u0640\u0640\u0640\u06d2\u0650\u0650");
                }
                if (other_ki.equals("\u0622")) {
                    list.add("\u0622");
                }
                if (other_ki.equals("\u0627")) {
                    list.add("\u0627");
                }
                if (other_ki.equals("\u0623")) {
                    list.add("\u0623");
                }
                if (other_ki.equals("\u0625")) {
                    list.add("\u0625");
                }
                if (other_ki.equals("\u0636")) {
                    list.add("\u0636\u064b");
                }
                if (other_ki.equals("\u062b")) {
                    list.add("\u062b");
                }
                if (other_ki.equals("\u0630")) {
                    list.add("\u0690");
                }
                if (other_ki.equals("\u0632")) {
                    list.add("\u0632");
                }
                if (other_ki.equals("\u0698")) {
                    list.add("\u0699");
                }
                if (other_ki.equals("\u0642")) {
                    list.add("\u0642");
                }
                if (other_ki.equals("\u0641")) {
                    list.add("\u06a4");
                }
                if (other_ki.equals("\u063a")) {
                    list.add("\u06fc");
                }
                if (other_ki.equals("\u0639")) {
                    list.add("\u0639");
                }
                if (other_ki.equals("\u062e")) {
                    list.add("\u0682");
                }
                if (other_ki.equals("\u062c")) {
                    list.add("\u062c");
                }
                if (other_ki.equals("\u0686")) {
                    list.add("\u06bf");
                }
                if (other_ki.equals("\u062f")) {
                    list.add("\u062f");
                }
                if (other_ki.equals("\u0628")) {
                    list.add("\u067b");
                }
                if (other_ki.equals("\u0644")) {
                    list.add("\u06b8");
                }
                if (other_ki.equals("\u0633")) {
                    list.add("\u069b");
                }
                if (other_ki.equals("\u06a9")) {
                    list.add("\u06ac");
                }
                if (other_ki.equals("\u06af")) {
                    list.add("\u06b2");
                }
                if (other_ki.equals("\u067e")) {
                    list.add("\u0752");
                }
                if (other_ki.equals("\u0631")) {
                    list.add("\u0631");
                }
                if (other_ki.equals("\u0648")) {
                    list.add("\u06cb");
                }
                if (other_ki.equals("\u0645")) {
                    list.add("\u0645");
                }
                if (other_ki.equals("\u062a")) {
                    list.add("\u062a");
                }
                if (other_ki.equals("\u0634")) {
                    list.add("\u075c\u064e");
                }
                if (other_ki.equals("\u0646")) {
                    list.add("\u06b9");
                }
                if (other_ki.equals("\u0637")) {
                    list.add("\u0637");
                }
                if (other_ki.equals("\u062d")) {
                    list.add("\u062d");
                }
                if (other_ki.equals("\u0638")) {
                    list.add("\u0638");
                }
                if (other_ki.equals(" ")) {
                    list.add(" ");
                }
            } else if (i <= index.length) {
                other_ki = index[i];
                if (other_ki.equals("q") || other_ki.equals("Q")) {
                    list.add("Q");
                }
                if (other_ki.equals("w") || other_ki.equals("W")) {
                    list.add("\u2c72");
                }
                if (other_ki.equals("e") || other_ki.equals("E")) {
                    list.add("\u018e");
                }
                if (other_ki.equals("r") || other_ki.equals("R")) {
                    list.add("\u0212");
                }
                if (other_ki.equals("t") || other_ki.equals("T")) {
                    list.add("\u01ae");
                }
                if (other_ki.equals("y") || other_ki.equals("Y")) {
                    list.add("\u01b3");
                }
                if (other_ki.equals("u") || other_ki.equals("U")) {
                    list.add("\u01af");
                }
                if (other_ki.equals("i") || other_ki.equals("I")) {
                    list.add("\u0197");
                }
                if (other_ki.equals("o") || other_ki.equals("O")) {
                    list.add("\u019f");
                }
                if (other_ki.equals(TtmlNode.TAG_P) || other_ki.equals("P")) {
                    list.add("\u1e56");
                }
                if (other_ki.equals("a") || other_ki.equals("A")) {
                    list.add("\u0104");
                }
                if (other_ki.equals("s") || other_ki.equals("S")) {
                    list.add("\u2c7e");
                }
                if (other_ki.equals("d") || other_ki.equals("D")) {
                    list.add("\u0189");
                }
                if (other_ki.equals("f") || other_ki.equals("F")) {
                    list.add("\u0191");
                }
                if (other_ki.equals("g") || other_ki.equals("G")) {
                    list.add("\u01e6");
                }
                if (other_ki.equals("h") || other_ki.equals("H")) {
                    list.add("\u0126");
                }
                if (other_ki.equals("j") || other_ki.equals("J")) {
                    list.add("J");
                }
                if (other_ki.equals("k") || other_ki.equals("K")) {
                    list.add("\u2c69");
                }
                if (other_ki.equals("l") || other_ki.equals("L")) {
                    list.add("\u2c62");
                }
                if (other_ki.equals("z") || other_ki.equals("Z")) {
                    list.add("\u2c7f");
                }
                if (other_ki.equals("x") || other_ki.equals("X")) {
                    list.add("\u1e8a");
                }
                if (other_ki.equals("c") || other_ki.equals("C")) {
                    list.add("\u0187");
                }
                if (other_ki.equals("v") || other_ki.equals("V")) {
                    list.add("\u2c71");
                }
                if (other_ki.equals("b") || other_ki.equals("B")) {
                    list.add("\u0181");
                }
                if (other_ki.equals("n") || other_ki.equals("N")) {
                    list.add("\u019d");
                }
                if (other_ki.equals("m") || other_ki.equals("M")) {
                    list.add("\u1e42");
                }
                if (other_ki.equals("?")) {
                    list.add("?");
                }
                if (other_ki.equals("\u0635")) {
                    list.add("\u0635");
                }
                if (other_ki.equals("\u0635")) {
                    list.add("\u0635");
                }
                if (other_ki.equals("?")) {
                    list.add("?");
                }
                if (other_ki.equals("\u061f")) {
                    list.add("\u061f");
                }
                if (other_ki.equals(".")) {
                    list.add(".");
                }
                if (other_ki.equals("!")) {
                    list.add("!");
                }
                if (other_ki.equals("@")) {
                    list.add("@");
                }
                if (other_ki.equals("#")) {
                    list.add("#");
                }
                if (other_ki.equals("$")) {
                    list.add("$");
                }
                if (other_ki.equals("%")) {
                    list.add("%");
                }
                if (other_ki.equals("^")) {
                    list.add("^");
                }
                if (other_ki.equals("&")) {
                    list.add("&");
                }
                if (other_ki.equals(")")) {
                    list.add(")");
                }
                if (other_ki.equals("(")) {
                    list.add("(");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("_")) {
                    list.add("_");
                }
                if (other_ki.equals("=")) {
                    list.add("=");
                }
                if (other_ki.equals("+")) {
                    list.add("+");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("*")) {
                    list.add("*");
                }
                if (other_ki.equals("/")) {
                    list.add("/");
                }
                if (other_ki.equals("\u060c")) {
                    list.add("\u060c");
                }
                if (other_ki.equals("\u2019")) {
                    list.add("\u2019");
                }
                if (other_ki.equals(":")) {
                    list.add(":");
                }
                if (other_ki.equals("<")) {
                    list.add("<");
                }
                if (other_ki.equals(">")) {
                    list.add(">");
                }
                if (other_ki.equals("\u061b")) {
                    list.add("\u061b");
                }
                if (other_ki.equals("\u00a9")) {
                    list.add("\u00a9");
                }
                if (other_ki.equals("}")) {
                    list.add("}");
                }
                if (other_ki.equals("{")) {
                    list.add("{");
                }
                if (other_ki.equals("[")) {
                    list.add("[");
                }
                if (other_ki.equals("]")) {
                    list.add("]");
                }
                if (other_ki.equals("\u00ab")) {
                    list.add("\u00ab");
                }
                if (other_ki.equals("\u00bb")) {
                    list.add("\u00bb");
                }
                if (other_ki.equals("\u06f1")) {
                    list.add("\u06f1");
                }
                if (other_ki.equals("\u06f2")) {
                    list.add("\u06f2");
                }
                if (other_ki.equals("\u06f3")) {
                    list.add("\u06f3");
                }
                if (other_ki.equals("\u06f4")) {
                    list.add("\u06f4");
                }
                if (other_ki.equals("\u06f5")) {
                    list.add("\u06f5");
                }
                if (other_ki.equals("\u06f7")) {
                    list.add("\u06f7");
                }
                if (other_ki.equals("\u06f6")) {
                    list.add("\u06f6");
                }
                if (other_ki.equals("\u06f8")) {
                    list.add("\u06f8");
                }
                if (other_ki.equals("\u06f9")) {
                    list.add("\u06f9");
                }
                if (other_ki.equals("\u06f0")) {
                    list.add("\u06f0");
                }
                if (other_ki.equals("1")) {
                    list.add("1");
                }
                if (other_ki.equals("2")) {
                    list.add("2");
                }
                if (other_ki.equals("3")) {
                    list.add("3");
                }
                if (other_ki.equals("4")) {
                    list.add("4");
                }
                if (other_ki.equals("5")) {
                    list.add("5");
                }
                if (other_ki.equals("6")) {
                    list.add("6");
                }
                if (other_ki.equals("7")) {
                    list.add("7");
                }
                if (other_ki.equals("8")) {
                    list.add("8");
                }
                if (other_ki.equals("9")) {
                    list.add("9");
                }
                if (other_ki.equals("0")) {
                    list.add("0");
                }
                if (other_ki.equals("\u0622")) {
                    list.add("\u0622");
                }
                if (other_ki.equals("\u0627")) {
                    list.add("\u0627");
                }
                if (other_ki.equals("\u0623")) {
                    list.add("\u0623");
                }
                if (other_ki.equals("\u0625")) {
                    list.add("\u0625");
                }
                if (other_ki.equals("\u0636")) {
                    list.add("\u0636\u064b");
                }
                if (other_ki.equals("\u062b")) {
                    list.add("\u062b");
                }
                if (other_ki.equals("\u0630")) {
                    list.add("\u0690");
                }
                if (other_ki.equals("\u0632")) {
                    list.add("\u0632");
                }
                if (other_ki.equals("\u0698")) {
                    list.add("\u0699");
                }
                if (other_ki.equals("\u0642")) {
                    list.add("\u0642");
                }
                if (other_ki.equals("\u0641")) {
                    list.add("\u06a4");
                }
                if (other_ki.equals("\u063a")) {
                    list.add("\u06fc");
                }
                if (other_ki.equals("\u0639")) {
                    list.add("\u0639");
                }
                if (other_ki.equals("\u062e")) {
                    list.add("\u0682");
                }
                if (other_ki.equals("\u062c")) {
                    list.add("\u062c");
                }
                if (other_ki.equals("\u0686")) {
                    list.add("\u06bf");
                }
                if (other_ki.equals("\u062f")) {
                    list.add("\u062f");
                }
                if (other_ki.equals("\u0628")) {
                    list.add("\u067b");
                }
                if (other_ki.equals("\u0644")) {
                    list.add("\u06b8");
                }
                if (other_ki.equals("\u06cc")) {
                    list.add("\u06cc");
                }
                if (other_ki.equals("\u0633")) {
                    list.add("\u069b");
                }
                if (other_ki.equals("\u06a9")) {
                    list.add("\u06ac");
                }
                if (other_ki.equals("\u06af")) {
                    list.add("\u06b2");
                }
                if (other_ki.equals("\u067e")) {
                    list.add("\u0752");
                }
                if (other_ki.equals("\u0631")) {
                    list.add("\u0631");
                }
                if (other_ki.equals("\u0648")) {
                    list.add("\u06cb");
                }
                if (other_ki.equals("\u0645")) {
                    list.add("\u0645");
                }
                if (other_ki.equals("\u062a")) {
                    list.add("\u062a");
                }
                if (other_ki.equals("\u0647")) {
                    list.add("\u0647");
                }
                if (other_ki.equals("\u0634")) {
                    list.add("\u075c\u064e");
                }
                if (other_ki.equals("\u0646")) {
                    list.add("\u06b9");
                }
                if (other_ki.equals("\u0637")) {
                    list.add("\u0637");
                }
                if (other_ki.equals("\u062d")) {
                    list.add("\u062d");
                }
                if (other_ki.equals("\u0638")) {
                    list.add("\u0638");
                }
                if (other_ki.equals(" ")) {
                    list.add(" ");
                }
            }
        }
        return change_en;
    }

    public String Change_name3(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 1; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("q") || other_ki.equals("Q")) {
                    list.add("\u24c6");
                }
                if (other_ki.equals("w") || other_ki.equals("W")) {
                    list.add("\u24cc");
                }
                if (other_ki.equals("e") || other_ki.equals("E")) {
                    list.add("\u24ba");
                }
                if (other_ki.equals("r") || other_ki.equals("R")) {
                    list.add("\u24c7");
                }
                if (other_ki.equals("t") || other_ki.equals("T")) {
                    list.add("\u24c9");
                }
                if (other_ki.equals("y") || other_ki.equals("Y")) {
                    list.add("\u24ce");
                }
                if (other_ki.equals("u") || other_ki.equals("U")) {
                    list.add("\u24ca");
                }
                if (other_ki.equals("i") || other_ki.equals("I")) {
                    list.add("\u24be");
                }
                if (other_ki.equals("o") || other_ki.equals("O")) {
                    list.add("\u24c4");
                }
                if (other_ki.equals(TtmlNode.TAG_P) || other_ki.equals("P")) {
                    list.add("\u24c5");
                }
                if (other_ki.equals("a") || other_ki.equals("A")) {
                    list.add("\u24b6");
                }
                if (other_ki.equals("s") || other_ki.equals("S")) {
                    list.add("\u24c8");
                }
                if (other_ki.equals("d") || other_ki.equals("D")) {
                    list.add("\u24b9");
                }
                if (other_ki.equals("f") || other_ki.equals("F")) {
                    list.add("\u24bb");
                }
                if (other_ki.equals("g") || other_ki.equals("G")) {
                    list.add("\u24bc");
                }
                if (other_ki.equals("h") || other_ki.equals("H")) {
                    list.add("\u24bd");
                }
                if (other_ki.equals("j") || other_ki.equals("J")) {
                    list.add("\u24bf");
                }
                if (other_ki.equals("k") || other_ki.equals("K")) {
                    list.add("\u24c0");
                }
                if (other_ki.equals("l") || other_ki.equals("L")) {
                    list.add("\u24c1");
                }
                if (other_ki.equals("z") || other_ki.equals("Z")) {
                    list.add("\u24cf");
                }
                if (other_ki.equals("x") || other_ki.equals("X")) {
                    list.add("\u24cd");
                }
                if (other_ki.equals("c") || other_ki.equals("C")) {
                    list.add("\u24b8");
                }
                if (other_ki.equals("v") || other_ki.equals("V")) {
                    list.add("\u24cb");
                }
                if (other_ki.equals("b") || other_ki.equals("B")) {
                    list.add("\u24b7");
                }
                if (other_ki.equals("n") || other_ki.equals("N")) {
                    list.add("\u24c3");
                }
                if (other_ki.equals("m") || other_ki.equals("M")) {
                    list.add("\u24c2");
                }
                if (other_ki.equals("?")) {
                    list.add("?");
                }
                if (other_ki.equals("\u0635")) {
                    list.add("\u0635");
                }
                if (other_ki.equals("\u061f")) {
                    list.add("\u061f");
                }
                if (other_ki.equals(".")) {
                    list.add(".");
                }
                if (other_ki.equals("!")) {
                    list.add("!");
                }
                if (other_ki.equals("@")) {
                    list.add("@");
                }
                if (other_ki.equals("#")) {
                    list.add("#");
                }
                if (other_ki.equals("$")) {
                    list.add("$");
                }
                if (other_ki.equals("%")) {
                    list.add("%");
                }
                if (other_ki.equals("^")) {
                    list.add("^");
                }
                if (other_ki.equals("&")) {
                    list.add("&");
                }
                if (other_ki.equals(")")) {
                    list.add(")");
                }
                if (other_ki.equals("(")) {
                    list.add("(");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("_")) {
                    list.add("_");
                }
                if (other_ki.equals("=")) {
                    list.add("=");
                }
                if (other_ki.equals("+")) {
                    list.add("+");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("*")) {
                    list.add("*");
                }
                if (other_ki.equals("/")) {
                    list.add("/");
                }
                if (other_ki.equals("\u060c")) {
                    list.add("\u060c");
                }
                if (other_ki.equals("\u2019")) {
                    list.add("\u2019");
                }
                if (other_ki.equals(":")) {
                    list.add(":");
                }
                if (other_ki.equals("<")) {
                    list.add("<");
                }
                if (other_ki.equals(">")) {
                    list.add(">");
                }
                if (other_ki.equals("\u061b")) {
                    list.add("\u061b");
                }
                if (other_ki.equals("\u00a9")) {
                    list.add("\u00a9");
                }
                if (other_ki.equals("}")) {
                    list.add("}");
                }
                if (other_ki.equals("{")) {
                    list.add("{");
                }
                if (other_ki.equals("[")) {
                    list.add("[");
                }
                if (other_ki.equals("]")) {
                    list.add("]");
                }
                if (other_ki.equals("\u00ab")) {
                    list.add("\u00ab");
                }
                if (other_ki.equals("\u00bb")) {
                    list.add("\u00bb");
                }
                if (other_ki.equals("\u06f1")) {
                    list.add("\u06f1");
                }
                if (other_ki.equals("\u06f2")) {
                    list.add("\u06f2");
                }
                if (other_ki.equals("\u06f3")) {
                    list.add("\u06f3");
                }
                if (other_ki.equals("\u06f4")) {
                    list.add("\u06f4");
                }
                if (other_ki.equals("\u06f5")) {
                    list.add("\u06f5");
                }
                if (other_ki.equals("\u06f7")) {
                    list.add("\u06f7");
                }
                if (other_ki.equals("\u06f6")) {
                    list.add("\u06f6");
                }
                if (other_ki.equals("\u06f8")) {
                    list.add("\u06f8");
                }
                if (other_ki.equals("\u06f9")) {
                    list.add("\u06f9");
                }
                if (other_ki.equals("\u06f0")) {
                    list.add("\u06f0");
                }
                if (other_ki.equals("1")) {
                    list.add("1");
                }
                if (other_ki.equals("2")) {
                    list.add("2");
                }
                if (other_ki.equals("3")) {
                    list.add("3");
                }
                if (other_ki.equals("4")) {
                    list.add("4");
                }
                if (other_ki.equals("5")) {
                    list.add("5");
                }
                if (other_ki.equals("6")) {
                    list.add("6");
                }
                if (other_ki.equals("7")) {
                    list.add("7");
                }
                if (other_ki.equals("8")) {
                    list.add("8");
                }
                if (other_ki.equals("9")) {
                    list.add("9");
                }
                if (other_ki.equals("0")) {
                    list.add("0");
                }
                if (other_ki.equals("\u0622")) {
                    list.add("\u0622");
                }
                if (other_ki.equals("\u0627")) {
                    list.add("\u0627");
                }
                if (other_ki.equals("\u0623")) {
                    list.add("\u0623");
                }
                if (other_ki.equals("\u0625")) {
                    list.add("\u0625");
                }
                if (other_ki.equals("\u0636")) {
                    list.add("\u06fb");
                }
                if (other_ki.equals("\u062b")) {
                    list.add("\u062b");
                }
                if (other_ki.equals("\u0630")) {
                    list.add("\u068e");
                }
                if (other_ki.equals("\u0632")) {
                    list.add("\u0632");
                }
                if (other_ki.equals("\u0698")) {
                    list.add("\u0697");
                }
                if (other_ki.equals("\u0642")) {
                    list.add("\u06a8");
                }
                if (other_ki.equals("\u0641")) {
                    list.add("\u0641");
                }
                if (other_ki.equals("\u063a")) {
                    list.add("\u075d");
                }
                if (other_ki.equals("\u0639")) {
                    list.add("\u0639");
                }
                if (other_ki.equals("\u062e")) {
                    list.add("\u062e");
                }
                if (other_ki.equals("\u062c")) {
                    list.add("\u0683");
                }
                if (other_ki.equals("\u0686")) {
                    list.add("\u0685");
                }
                if (other_ki.equals("\u062f")) {
                    list.add("\u062f");
                }
                if (other_ki.equals("\u0628")) {
                    list.add("\u066e\u0651");
                }
                if (other_ki.equals("\u0644")) {
                    list.add("\u06b7");
                }
                if (other_ki.equals("\u06cc")) {
                    list.add("\u0626");
                }
                if (other_ki.equals("\u0633")) {
                    list.add("\u069a\u064f");
                }
                if (other_ki.equals("\u06a9")) {
                    list.add("\u0763");
                }
                if (other_ki.equals("\u06af")) {
                    list.add("\u06b1");
                }
                if (other_ki.equals("\u067e")) {
                    list.add("\u0750");
                }
                if (other_ki.equals("\u0631")) {
                    list.add("\u0694");
                }
                if (other_ki.equals("\u0648")) {
                    list.add("\u06ca");
                }
                if (other_ki.equals("\u0645")) {
                    list.add("\u0765");
                }
                if (other_ki.equals("\u062a")) {
                    list.add("\u067c");
                }
                if (other_ki.equals("\u0647")) {
                    list.add("\u0647");
                }
                if (other_ki.equals("\u0634")) {
                    list.add("\u0634");
                }
                if (other_ki.equals("\u0646")) {
                    list.add("\u0769");
                }
                if (other_ki.equals("\u0637")) {
                    list.add("\u0637");
                }
                if (other_ki.equals("\u062d")) {
                    list.add("\u0681");
                }
                if (other_ki.equals("\u0638")) {
                    list.add("\u0638");
                }
                if (other_ki.equals(" ")) {
                    list.add(" ");
                }
            } else if (i <= index.length) {
                if (other_ki.equals("q") || other_ki.equals("Q")) {
                    list.add("\u24c6");
                }
                if (other_ki.equals("w") || other_ki.equals("W")) {
                    list.add("\u24cc");
                }
                if (other_ki.equals("e") || other_ki.equals("E")) {
                    list.add("\u24ba");
                }
                if (other_ki.equals("r") || other_ki.equals("R")) {
                    list.add("\u24c7");
                }
                if (other_ki.equals("t") || other_ki.equals("T")) {
                    list.add("\u24c9");
                }
                if (other_ki.equals("y") || other_ki.equals("Y")) {
                    list.add("\u24ce");
                }
                if (other_ki.equals("u") || other_ki.equals("U")) {
                    list.add("\u24ca");
                }
                if (other_ki.equals("i") || other_ki.equals("I")) {
                    list.add("\u24be");
                }
                if (other_ki.equals("o") || other_ki.equals("O")) {
                    list.add("\u24c4");
                }
                if (other_ki.equals(TtmlNode.TAG_P) || other_ki.equals("P")) {
                    list.add("\u24c5");
                }
                if (other_ki.equals("a") || other_ki.equals("A")) {
                    list.add("\u24b6");
                }
                if (other_ki.equals("s") || other_ki.equals("S")) {
                    list.add("\u24c8");
                }
                if (other_ki.equals("d") || other_ki.equals("D")) {
                    list.add("\u24b9");
                }
                if (other_ki.equals("f") || other_ki.equals("F")) {
                    list.add("\u24bb");
                }
                if (other_ki.equals("g") || other_ki.equals("G")) {
                    list.add("\u24bc");
                }
                if (other_ki.equals("h") || other_ki.equals("H")) {
                    list.add("\u24bd");
                }
                if (other_ki.equals("j") || other_ki.equals("J")) {
                    list.add("\u24bf");
                }
                if (other_ki.equals("k") || other_ki.equals("K")) {
                    list.add("\u24c0");
                }
                if (other_ki.equals("l") || other_ki.equals("L")) {
                    list.add("\u24c1");
                }
                if (other_ki.equals("z") || other_ki.equals("Z")) {
                    list.add("\u24cf");
                }
                if (other_ki.equals("x") || other_ki.equals("X")) {
                    list.add("\u24cd");
                }
                if (other_ki.equals("c") || other_ki.equals("C")) {
                    list.add("\u24b8");
                }
                if (other_ki.equals("v") || other_ki.equals("V")) {
                    list.add("\u24cb");
                }
                if (other_ki.equals("b") || other_ki.equals("B")) {
                    list.add("\u24b7");
                }
                if (other_ki.equals("n") || other_ki.equals("N")) {
                    list.add("\u24c3");
                }
                if (other_ki.equals("m") || other_ki.equals("M")) {
                    list.add("\u24c2");
                }
                if (other_ki.equals("?")) {
                    list.add("?");
                }
                if (other_ki.equals("\u061f")) {
                    list.add("\u061f");
                }
                if (other_ki.equals("\u0635")) {
                    list.add("\u0635");
                }
                if (other_ki.equals(".")) {
                    list.add(".");
                }
                if (other_ki.equals("!")) {
                    list.add("!");
                }
                if (other_ki.equals("@")) {
                    list.add("@");
                }
                if (other_ki.equals("#")) {
                    list.add("#");
                }
                if (other_ki.equals("$")) {
                    list.add("$");
                }
                if (other_ki.equals("%")) {
                    list.add("%");
                }
                if (other_ki.equals("^")) {
                    list.add("^");
                }
                if (other_ki.equals("&")) {
                    list.add("&");
                }
                if (other_ki.equals(")")) {
                    list.add(")");
                }
                if (other_ki.equals("(")) {
                    list.add("(");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("_")) {
                    list.add("_");
                }
                if (other_ki.equals("=")) {
                    list.add("=");
                }
                if (other_ki.equals("+")) {
                    list.add("+");
                }
                if (other_ki.equals("-")) {
                    list.add("-");
                }
                if (other_ki.equals("*")) {
                    list.add("*");
                }
                if (other_ki.equals("/")) {
                    list.add("/");
                }
                if (other_ki.equals("\u060c")) {
                    list.add("\u060c");
                }
                if (other_ki.equals("\u2019")) {
                    list.add("\u2019");
                }
                if (other_ki.equals(":")) {
                    list.add(":");
                }
                if (other_ki.equals("<")) {
                    list.add("<");
                }
                if (other_ki.equals(">")) {
                    list.add(">");
                }
                if (other_ki.equals("\u061b")) {
                    list.add("\u061b");
                }
                if (other_ki.equals("\u00a9")) {
                    list.add("\u00a9");
                }
                if (other_ki.equals("}")) {
                    list.add("}");
                }
                if (other_ki.equals("{")) {
                    list.add("{");
                }
                if (other_ki.equals("[")) {
                    list.add("[");
                }
                if (other_ki.equals("]")) {
                    list.add("]");
                }
                if (other_ki.equals("\u00ab")) {
                    list.add("\u00ab");
                }
                if (other_ki.equals("\u00bb")) {
                    list.add("\u00bb");
                }
                if (other_ki.equals("\u06f1")) {
                    list.add("\u06f1");
                }
                if (other_ki.equals("\u06f2")) {
                    list.add("\u06f2");
                }
                if (other_ki.equals("\u06f3")) {
                    list.add("\u06f3");
                }
                if (other_ki.equals("\u06f4")) {
                    list.add("\u06f4");
                }
                if (other_ki.equals("\u06f5")) {
                    list.add("\u06f5");
                }
                if (other_ki.equals("\u06f7")) {
                    list.add("\u06f7");
                }
                if (other_ki.equals("\u06f6")) {
                    list.add("\u06f6");
                }
                if (other_ki.equals("\u06f8")) {
                    list.add("\u06f8");
                }
                if (other_ki.equals("\u06f9")) {
                    list.add("\u06f9");
                }
                if (other_ki.equals("\u06f0")) {
                    list.add("\u06f0");
                }
                if (other_ki.equals("1")) {
                    list.add("1");
                }
                if (other_ki.equals("2")) {
                    list.add("2");
                }
                if (other_ki.equals("3")) {
                    list.add("3");
                }
                if (other_ki.equals("4")) {
                    list.add("4");
                }
                if (other_ki.equals("5")) {
                    list.add("5");
                }
                if (other_ki.equals("6")) {
                    list.add("6");
                }
                if (other_ki.equals("7")) {
                    list.add("7");
                }
                if (other_ki.equals("8")) {
                    list.add("8");
                }
                if (other_ki.equals("9")) {
                    list.add("9");
                }
                if (other_ki.equals("0")) {
                    list.add("0");
                }
                if (other_ki.equals("\u0622")) {
                    list.add("\u0622");
                }
                if (other_ki.equals("\u0627")) {
                    list.add("\u0627");
                }
                if (other_ki.equals("\u0623")) {
                    list.add("\u0623");
                }
                if (other_ki.equals("\u0625")) {
                    list.add("\u0625");
                }
                if (other_ki.equals("\u0636")) {
                    list.add("\u06fb");
                }
                if (other_ki.equals("\u062b")) {
                    list.add("\u062b");
                }
                if (other_ki.equals("\u0630")) {
                    list.add("\u068e");
                }
                if (other_ki.equals("\u0632")) {
                    list.add("\u0632");
                }
                if (other_ki.equals("\u0698")) {
                    list.add("\u0697");
                }
                if (other_ki.equals("\u0642")) {
                    list.add("\u06a8");
                }
                if (other_ki.equals("\u0641")) {
                    list.add("\u0641");
                }
                if (other_ki.equals("\u063a")) {
                    list.add("\u075d");
                }
                if (other_ki.equals("\u0639")) {
                    list.add("\u0639");
                }
                if (other_ki.equals("\u062e")) {
                    list.add("\u062e");
                }
                if (other_ki.equals("\u062c")) {
                    list.add("\u0683");
                }
                if (other_ki.equals("\u0686")) {
                    list.add("\u0685");
                }
                if (other_ki.equals("\u062f")) {
                    list.add("\u062f");
                }
                if (other_ki.equals("\u0628")) {
                    list.add("\u066e\u0651");
                }
                if (other_ki.equals("\u0644")) {
                    list.add("\u06b7");
                }
                if (other_ki.equals("\u06cc")) {
                    list.add("\u0626");
                }
                if (other_ki.equals("\u0633")) {
                    list.add("\u069a\u064f");
                }
                if (other_ki.equals("\u06a9")) {
                    list.add("\u0763");
                }
                if (other_ki.equals("\u06af")) {
                    list.add("\u06b1");
                }
                if (other_ki.equals("\u067e")) {
                    list.add("\u0750");
                }
                if (other_ki.equals("\u0631")) {
                    list.add("\u0694");
                }
                if (other_ki.equals("\u0648")) {
                    list.add("\u06ca");
                }
                if (other_ki.equals("\u0645")) {
                    list.add("\u0765");
                }
                if (other_ki.equals("\u062a")) {
                    list.add("\u067c");
                }
                if (other_ki.equals("\u0647")) {
                    list.add("\u0647");
                }
                if (other_ki.equals("\u0634")) {
                    list.add("\u0634");
                }
                if (other_ki.equals("\u0646")) {
                    list.add("\u0769");
                }
                if (other_ki.equals("\u0637")) {
                    list.add("\u0637");
                }
                if (other_ki.equals("\u062d")) {
                    list.add("\u0681");
                }
                if (other_ki.equals("\u0638")) {
                    list.add("\u0638");
                }
                if (other_ki.equals(" ")) {
                    list.add(" ");
                }
            }
        }
        return change_en;
    }

    public String Change_name4(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u06d2");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u0305\u0305\u06aa\u0305\u0305");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u0305\u0305\u06cc\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0644")) {
                    list.add("\u0305\u0305\u0644\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0636")) {
                    list.add("\u0305\u0305\u0636\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u062b")) {
                    list.add("\u0305\u0305\u062b\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0642")) {
                    list.add("\u0305\u0305\u0642\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0641")) {
                    list.add("\u0305\u0305\u0641\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u063a")) {
                    list.add("\u0305\u0305\u063a\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0639")) {
                    list.add("\u0305\u0305\u0639\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u062e")) {
                    list.add("\u0305\u0305\u062e\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u062c")) {
                    list.add("\u0305\u0305\u062c\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0686")) {
                    list.add("\u0305\u0305\u0686\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0628")) {
                    list.add("\u0305\u0305\u0628\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0633")) {
                    list.add("\u0305\u0305\u0633\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u0305\u0305\u06aa\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u06af")) {
                    list.add("\u0305\u0305\u06af\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u067e")) {
                    list.add("\u0305\u0305\u067e\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0645")) {
                    list.add("\u0305\u0305\u0645\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u062a")) {
                    list.add("\u0305\u0305\u062a\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0634")) {
                    list.add("\u0305\u0305\u0634\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0646")) {
                    list.add("\u0305\u0305\u0646\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0637")) {
                    list.add("\u0305\u0305\u0637\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u062d")) {
                    list.add("\u0305\u0305\u062d\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0638")) {
                    list.add("\u0305\u0305\u0638\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else if (other_ki.equals("\u0635")) {
                    list.add("\u0305\u0305\u0635\u0305\u0305\u0640\u0305\u0305\u0640\u0305\u0305");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }


    public  String Change_name5(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u0311\u06d2\u0311\u0650");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u0311\u06aa\u0311");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u0311\u06cc\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0644")) {
                    list.add("\u0311\u0644\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0636")) {
                    list.add("\u0311\u0636\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u062b")) {
                    list.add("\u0311\u062b\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0642")) {
                    list.add("\u0311\u0642\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0641")) {
                    list.add("\u0311\u0641\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u063a")) {
                    list.add("\u0311\u063a\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0639")) {
                    list.add("\u0311\u0639\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u062e")) {
                    list.add("\u0311\u062e\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u062c")) {
                    list.add("\u0311\u062c\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0686")) {
                    list.add("\u0311\u0686\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0628")) {
                    list.add("\u0311\u0628\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0633")) {
                    list.add("\u0311\u0633\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u0311\u06aa\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u06af")) {
                    list.add("\u0311\u06af\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u067e")) {
                    list.add("\u0311\u067e\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0645")) {
                    list.add("\u0311\u0645\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u062a")) {
                    list.add("\u0311\u062a\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0634")) {
                    list.add("\u0311\u0634\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0646")) {
                    list.add("\u0311\u0646\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0637")) {
                    list.add("\u0311\u0637\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u062d")) {
                    list.add("\u0311\u062d\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0638")) {
                    list.add("\u0311\u0638\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0635")) {
                    list.add("\u0311\u0635\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public  String Change_name6(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u0640\u0640\u06d2\u0650");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u06aa");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("\u0622")) {
                    list.add("\u0622");
                } else if (other_ki.equals("\u0627")) {
                    list.add("\u0627");
                } else if (other_ki.equals("\u0623")) {
                    list.add("\u0623");
                } else if (other_ki.equals("\u0625")) {
                    list.add("\u0625");
                } else if (other_ki.equals("\u0636")) {
                    list.add("\u06fb");
                } else if (other_ki.equals("\u0630")) {
                    list.add("\u0630");
                } else if (other_ki.equals("\u0632")) {
                    list.add("\u0632");
                } else if (other_ki.equals("\u0698")) {
                    list.add("\u0698");
                } else if (other_ki.equals("\u0642")) {
                    list.add("\u0642");
                } else if (other_ki.equals("\u0641")) {
                    list.add("\u06a3");
                } else if (other_ki.equals("\u063a")) {
                    list.add("\u063a");
                } else if (other_ki.equals("\u0639")) {
                    list.add("\u0639");
                } else if (other_ki.equals("\u062e")) {
                    list.add("\u062e");
                } else if (other_ki.equals("\u062c")) {
                    list.add("\u0684");
                } else if (other_ki.equals("\u0686")) {
                    list.add("\u0686");
                } else if (other_ki.equals("\u062f")) {
                    list.add("\u062f");
                } else if (other_ki.equals("\u0628")) {
                    list.add("\u0628");
                } else if (other_ki.equals("\u0644")) {
                    list.add("\u076a");
                } else if (other_ki.equals("\u06cc")) {
                    list.add("\u06cc");
                } else if (other_ki.equals("\u0633")) {
                    list.add("\u0633");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u06a9");
                } else if (other_ki.equals("\u06af")) {
                    list.add("\u06b3");
                } else if (other_ki.equals("\u067e")) {
                    list.add("\u0680");
                } else if (other_ki.equals("\u0631")) {
                    list.add("\u0694");
                } else if (other_ki.equals("\u0648")) {
                    list.add("\u06c5");
                } else if (other_ki.equals("\u0645")) {
                    list.add("\u0766");
                } else if (other_ki.equals("\u062a")) {
                    list.add("\u067c");
                } else if (other_ki.equals("\u0647")) {
                    list.add("\u0647");
                } else if (other_ki.equals("\u0634")) {
                    list.add("\u06fa");
                } else if (other_ki.equals("\u0646")) {
                    list.add("\u0767");
                } else if (other_ki.equals("\u0637")) {
                    list.add("\u0637");
                } else if (other_ki.equals("\u062d")) {
                    list.add("\u0681");
                } else if (other_ki.equals("\u062b")) {
                    list.add("\u062b");
                } else if (other_ki.equals("\u0638")) {
                    list.add("\u0638");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }


    public  String Change_name7(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u06d2");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u06aa");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u06cc");
                } else if (other_ki.equals("\u0644")) {
                    list.add("\u0644");
                } else if (other_ki.equals("\u0636")) {
                    list.add("\u0636");
                } else if (other_ki.equals("\u062b")) {
                    list.add("\u062b");
                } else if (other_ki.equals("\u0642")) {
                    list.add("\u0642");
                } else if (other_ki.equals("\u0641")) {
                    list.add("\u0641");
                } else if (other_ki.equals("\u063a")) {
                    list.add("\u063a");
                } else if (other_ki.equals("\u0639")) {
                    list.add("\u0639");
                } else if (other_ki.equals("\u062e")) {
                    list.add("\u062e");
                } else if (other_ki.equals("\u062c")) {
                    list.add("\u062c");
                } else if (other_ki.equals("\u0686")) {
                    list.add("\u0686");
                } else if (other_ki.equals("\u0628")) {
                    list.add("\u0628");
                } else if (other_ki.equals("\u0633")) {
                    list.add("\u0633");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u06aa");
                } else if (other_ki.equals("\u06af")) {
                    list.add("\u06af");
                } else if (other_ki.equals("\u067e")) {
                    list.add("\u067e");
                } else if (other_ki.equals("\u0645")) {
                    list.add("\u0645");
                } else if (other_ki.equals("\u062a")) {
                    list.add("\u062a");
                } else if (other_ki.equals("\u0634")) {
                    list.add("\u0634");
                } else if (other_ki.equals("\u0646")) {
                    list.add("\u0646");
                } else if (other_ki.equals("\u0637")) {
                    list.add("\u0637");
                } else if (other_ki.equals("\u062d")) {
                    list.add("\u062d");
                } else if (other_ki.equals("\u0638")) {
                    list.add("\u0638");
                } else if (other_ki.equals("\u0635")) {
                    list.add("\u0635");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public  String Change_name8(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u06d2");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u06aa");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u06cc\u0640\u0640");
                } else if (other_ki.equals("\u0644")) {
                    list.add("\u0644\u0640\u0640");
                } else if (other_ki.equals("\u0636")) {
                    list.add("\u0636\u0640\u0640");
                } else if (other_ki.equals("\u062b")) {
                    list.add("\u062b\u0640\u0640");
                } else if (other_ki.equals("\u0642")) {
                    list.add("\u0642\u0640\u0640");
                } else if (other_ki.equals("\u0641")) {
                    list.add("\u0641\u0640\u0640");
                } else if (other_ki.equals("\u063a")) {
                    list.add("\u063a\u0640\u0640");
                } else if (other_ki.equals("\u0639")) {
                    list.add("\u0639\u0640\u0640");
                } else if (other_ki.equals("\u062e")) {
                    list.add("\u062e\u0640\u0640");
                } else if (other_ki.equals("\u062c")) {
                    list.add("\u062c\u0640\u0640");
                } else if (other_ki.equals("\u0686")) {
                    list.add("\u0686\u0640\u0640");
                } else if (other_ki.equals("\u0628")) {
                    list.add("\u0628\u0640\u0640");
                } else if (other_ki.equals("\u0633")) {
                    list.add("\u0633\u0640\u0640");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u06aa\u0640\u0640");
                } else if (other_ki.equals("\u06af")) {
                    list.add("\u06af\u0640\u0640");
                } else if (other_ki.equals("\u067e")) {
                    list.add("\u067e\u0640\u0640");
                } else if (other_ki.equals("\u0645")) {
                    list.add("\u0645\u0640\u0640");
                } else if (other_ki.equals("\u062a")) {
                    list.add("\u062a\u0640\u0640");
                } else if (other_ki.equals("\u0634")) {
                    list.add("\u0634\u0640\u0640");
                } else if (other_ki.equals("\u0646")) {
                    list.add("\u0646\u0640\u0640");
                } else if (other_ki.equals("\u0637")) {
                    list.add("\u0637\u0640\u0640");
                } else if (other_ki.equals("\u062d")) {
                    list.add("\u062d\u0640\u0640");
                } else if (other_ki.equals("\u0638")) {
                    list.add("\u0638\u0640\u0640");
                } else if (other_ki.equals("\u0635")) {
                    list.add("\u0635\u0640\u0640");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public  String Change_name9(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u06d2");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u0344\u06aa\u0344");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u06cc\u0311\u0311\u0640\u0311\u0311\u0640\u0311");
                } else if (other_ki.equals("\u0644")) {
                    list.add("\u0344\u0644\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0636")) {
                    list.add("\u0344\u0636\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u062b")) {
                    list.add("\u0344\u062b\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0642")) {
                    list.add("\u0344\u0642\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0641")) {
                    list.add("\u0344\u0641\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u063a")) {
                    list.add("\u0344\u063a\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0639")) {
                    list.add("\u0344\u0639\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u062e")) {
                    list.add("\u0344\u062e\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u062c")) {
                    list.add("\u0344\u062c\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0686")) {
                    list.add("\u0344\u0686\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0628")) {
                    list.add("\u0344\u0628\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0633")) {
                    list.add("\u0344\u0633\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u0344\u06aa\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u06af")) {
                    list.add("\u0344\u06af\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u067e")) {
                    list.add("\u0344\u067e\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0645")) {
                    list.add("\u0344\u0645\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u062a")) {
                    list.add("\u0344\u062a\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0634")) {
                    list.add("\u0344\u0634\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0646")) {
                    list.add("\u0344\u0646\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0637")) {
                    list.add("\u0344\u0637\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u062d")) {
                    list.add("\u0344\u062d\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0638")) {
                    list.add("\u0344\u0638\u0344\u0640\u0344\u0640\u0344");
                } else if (other_ki.equals("\u0635")) {
                    list.add("\u0344\u0635\u0344\u0640\u0344\u0640\u0344");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }

    public  String Change_name10(String change_en) {
        String[] index = change_en.split(TtmlNode.ANONYMOUS_REGION_ID);
        for (int i = 0; i < index.length; i++) {
            other_ki = index[i];
            if (i == index.length - 2) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u06d2");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u06aa");
                } else if (other_ki.equals("\u0636")) {
                    list.add("\u0636");
                } else if (other_ki.equals("\u062b")) {
                    list.add("\u062b");
                } else if (other_ki.equals("\u0642")) {
                    list.add("\u0642");
                } else if (other_ki.equals("\u0641")) {
                    list.add("\u0641");
                } else if (other_ki.equals("\u063a")) {
                    list.add("\u063a");
                } else if (other_ki.equals("\u0639")) {
                    list.add("\u0639");
                } else if (other_ki.equals("\u062e")) {
                    list.add("\u062e");
                } else if (other_ki.equals("\u062c")) {
                    list.add("\u062c");
                } else if (other_ki.equals("\u0686")) {
                    list.add("\u0686");
                } else if (other_ki.equals("\u0628")) {
                    list.add("\u0628");
                } else if (other_ki.equals("\u0633")) {
                    list.add("\u0633");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u06a9");
                } else if (other_ki.equals("\u06af")) {
                    list.add("\u06af");
                } else if (other_ki.equals("\u067e")) {
                    list.add("\u067e");
                } else if (other_ki.equals("\u0645")) {
                    list.add("\u0645");
                } else if (other_ki.equals("\u062a")) {
                    list.add("\u062a");
                } else if (other_ki.equals("\u0634")) {
                    list.add("\u0634");
                } else if (other_ki.equals("\u0646")) {
                    list.add("\u0646");
                } else if (other_ki.equals("\u0637")) {
                    list.add("\u0637");
                } else if (other_ki.equals("\u062d")) {
                    list.add("\u062d");
                } else if (other_ki.equals("\u0638")) {
                    list.add("\u0638");
                } else if (other_ki.equals("\u0635")) {
                    list.add("\u0635");
                } else {
                    list.add(index[i]);
                }
            } else if (i <= index.length) {
                if (other_ki.equals("\u06cc")) {
                    list.add("\u0336\u0336\u06cc\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0644")) {
                    list.add("\u0336\u0336\u0644\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0636")) {
                    list.add("\u0336\u0336\u0636\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u062b")) {
                    list.add("\u0336\u0336\u062b\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0642")) {
                    list.add("\u0336\u0336\u0642\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0641")) {
                    list.add("\u0336\u0336\u0641\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u063a")) {
                    list.add("\u0336\u0336\u063a\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0639")) {
                    list.add("\u0336\u0336\u0639\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u062e")) {
                    list.add("\u0336\u0336\u062e\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u062c")) {
                    list.add("\u0336\u0336\u062c\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0686")) {
                    list.add("\u0336\u0336\u0686\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0628")) {
                    list.add("\u0336\u0336\u0628\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0633")) {
                    list.add("\u0336\u0336\u0633\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u06a9")) {
                    list.add("\u0336\u0336\u06aa\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u06af")) {
                    list.add("\u0336\u0336\u06af\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u067e")) {
                    list.add("\u0336\u0336\u067e\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0645")) {
                    list.add("\u0336\u0336\u0645\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u062a")) {
                    list.add("\u0336\u0336\u062a\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0634")) {
                    list.add("\u0336\u0336\u0634\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0646")) {
                    list.add("\u0336\u0336\u0646\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0637")) {
                    list.add("\u0336\u0336\u0637\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u062d")) {
                    list.add("\u0336\u0336\u062d\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0638")) {
                    list.add("\u0336\u0336\u0638\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else if (other_ki.equals("\u0635")) {
                    list.add("\u0336\u0336\u0635\u0336\u0336\u0640\u0336\u0336\u0640\u0336\u0336");
                } else {
                    list.add(index[i]);
                }
            }
        }
        return change_en;
    }







}
