import java.net.InetAddress;

public class InetAddressTest {
    public static void main(String[] args) throws Exception{
        String[] arg = new String[1];
//        arg[0] = "time-A.timefreq.bldrdoc.gov";
//        arg[0] = "localhost";
//        arg[0] = "www.baidu.com";
        arg[0] = "www.google.com";
        if(arg.length > 0){
            String host = arg[0];
            InetAddress[]  addresses = InetAddress.getAllByName(host);
            for(InetAddress inetAddress : addresses){
                byte[] addressBytes = inetAddress.getAddress();
                for(int i = 0;i<addressBytes.length;i++){
//                    System.out.println(addressBytes[i]);
                }
                System.out.println(inetAddress);
            }

            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
            System.out.println(localHostAddress.getHostAddress());
        }else {
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}
