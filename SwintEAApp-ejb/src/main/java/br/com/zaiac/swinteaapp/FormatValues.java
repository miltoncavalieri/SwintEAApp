package br.com.zaiac.swinteaapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class FormatValues {
    public static String formatCGC(String cgc) {
        String nCgc = StringUtils.leftPad(cgc, 15, "0");
        String nCgc1 = nCgc.substring(0, 3);
        String nCgc2 = nCgc.substring(3, 6);
        String nCgc3 = nCgc.substring(6, 9);
        String nCgc4 = nCgc.substring(9, 13);
        String nCgc5 = nCgc.substring(13, 15);
        String nCgcFmt = nCgc1 + "." + nCgc2 + "." + nCgc3 + "/" + nCgc4 + "-" + nCgc5;
//        System.out.println("CGC: " + cgc + " CGC FMT: " + nCgcFmt);
        return nCgcFmt;
    }
    
    public static String formatCPF(String cpf) {
        String nCpf = StringUtils.leftPad(cpf, 11, "0");
        String nCpf1 = nCpf.substring(0, 3);
        String nCpf2 = nCpf.substring(3, 6);
        String nCpf3 = nCpf.substring(6, 9);
        String nCpf4 = nCpf.substring(9, 11);
        String nCpfFmt = nCpf1 + "." + nCpf2 + "." + nCpf3 + "-" + nCpf4;
//        System.out.println("CPF: " + cpf + " CPF FMT: " + nCpfFmt);
        return nCpfFmt;
    }
    
    public static String formatData(Date data) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yy");
        return fmt.format(data);
    }
    
    
    
    public static String diretorioPb (Long pbuId, Date pbuDt, String subdir) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return "/webserver/pb/" + dateFormat.format(pbuDt) + "/" + pbuId + "/" + subdir + "/";
    }
    
}
