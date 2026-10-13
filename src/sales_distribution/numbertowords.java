package sales_distribution;
import java.text.DecimalFormat;
public class numbertowords {
	private static final String[] tensNames = {
	    "",
	    " Ten",
	    " Twenty",
	    " Thirty",
	    " Forty",
	    " Fifty",
	    " Sixty",
	    " Seventy",
	    " Eighty",
	    " Ninety"
	  };

	  private static final String[] numNames = {
	    "",
	    " One",
	    " Two",
	    " Three",
	    " Four",
	    " Five",
	    " Six",
	    " Seven",
	    " Eight",
	    " Nine",
	    " Ten",
	    " Eleven",
	    " Twelve",
	    " Thirteen",
	    " Fourteen",
	    " Fifteen",
	    " Sixteen",
	    " Seventeen",
	    " Eighteen",
	    " Nineteen"
	  };

	  
	  private static String convertLessThanOneThousand(int number) {
	    String soFar;

	    if (number % 100 < 20){
	      soFar = numNames[number % 100];
	      number /= 100;
	    }
	    else {
	      soFar = numNames[number % 10];
	      number /= 10;

	      soFar = tensNames[number % 10] + soFar;
	      number /= 10;
	    }
	    if (number == 0) return soFar;
	    return numNames[number] + " Hundred" + soFar;
	  }


	  public static String convert(double ttlamtword) {
	    // 0 to 999 999 999 999
	    if (ttlamtword == 0) { return "zero"; }

	    String snumber = Double.toString(ttlamtword);

	    // pad with "0"
	    String mask = "000000000000";
	    DecimalFormat df = new DecimalFormat(mask);
	    snumber = df.format(ttlamtword);

	    // XXXnnnnnnnnn
	    int billions = Integer.parseInt(snumber.substring(0,5));//100 crore
	    // nnnXXXnnnnnn
	    int millions  = Integer.parseInt(snumber.substring(5,7));//10 lakhs
	    // nnnnnnXXXnnn
	    int hundredThousands = Integer.parseInt(snumber.substring(7,9));//1 lakh
	    // nnnnnnnnnXXX
	    int thousands = Integer.parseInt(snumber.substring(9,12));

	    String tradBillions;
	    switch (billions) {
	    case 0:
	      tradBillions = "";
	      break;
	    case 1 :
	      tradBillions = convertLessThanOneThousand(billions)
	      + " Billion ";
	      break;
	    default :
	      tradBillions = convertLessThanOneThousand(billions)
	      + " Billion ";
	    }
	    String result =  tradBillions;

	    String tradMillions;
	    switch (millions) {
	    case 0:
	      tradMillions = "";
	      break;
	    case 1 :
	      tradMillions = convertLessThanOneThousand(millions)
	         + " Lakhs ";
	      break;
	    default :
	      tradMillions = convertLessThanOneThousand(millions)
	         + " Lakhs ";
	    }
	    result =  result + tradMillions;

	    String tradHundredThousands;
	    switch (hundredThousands) {
	    case 0:
	      tradHundredThousands = "";
	      break;
	    case 1 :
	      tradHundredThousands = "One Thousand ";
	      break;
	    default :
	      tradHundredThousands = convertLessThanOneThousand(hundredThousands)
	         + " Thousand ";
	    }
	    result =  result + tradHundredThousands;

	    String tradThousand;
	    tradThousand = convertLessThanOneThousand(thousands);
	    result =  result + tradThousand;

	    // remove extra spaces!
	    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	  }

	  /**
	   * testing
	   * @param args
	   */
	  public static void main(String[] args) {
//	    System.out.println("*** " + numbertowords.convert(0));
//	    System.out.println("*** " + numbertowords.convert(1));
//	    System.out.println("*** " + numbertowords.convert(16));
//	    System.out.println("*** " + numbertowords.convert(100));
//	    System.out.println("*** " + numbertowords.convert(118));
//	    System.out.println("*** " + numbertowords.convert(200));
//	    System.out.println("*** " + numbertowords.convert(219));
//	    System.out.println("*** " + numbertowords.convert(800));
//	    System.out.println("*** " + numbertowords.convert(801));
//	    System.out.println("*** " + numbertowords.convert(1316));
//	    System.out.println("*** " + numbertowords.convert(1000000));
//	    System.out.println("*** " + numbertowords.convert(2000000));
//	    System.out.println("*** " + numbertowords.convert(3000200));
//	    System.out.println("*** " + numbertowords.convert(700000));
//	    System.out.println("*** " + numbertowords.convert(9000000));
//	    System.out.println("*** " + numbertowords.convert(9001000));
//	    System.out.println("*** " + numbertowords.convert(123456789));
//	    System.out.println("*** " + numbertowords.convert(2147483647));
//	    System.out.println("*** " + numbertowords.convert(3000000010L));

	  }
}
