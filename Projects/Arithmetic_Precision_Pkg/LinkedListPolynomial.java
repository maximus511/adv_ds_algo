
import java.util.HashMap;
import java.util.LinkedList;

public class LinkedListPolynomial {

	/**
	 * @param args
	 */
	static String _sNegative_Error="Negative number not supported";
	static HashMap<Integer, String> hDynamicMap=new HashMap<Integer, String>();
	static final int nBase=1000000000;
	static final int noOfDigit=(int) Math.log10(nBase);

	static String m="4";
	static int n=10;

	/**
	 *
	 * @param args
	 * Method for testing
	 */

	
	/**
	 *
	 * @param sNum1
	 * @param sNum2
	 * @param cSign
	 * @return Perform the operation based on the sign
	 */
	public static String identifySignAndPerform(String sNum1, String sNum2, char cSign)
	{
		String sResult="";
		String sSign="-";
		String rootSign="i";
		if(cSign != 'r')
        {
            if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)=='-')&&(cSign=='+'))
		{
			sNum1=sNum1.substring(1);
			sNum2=sNum2.substring(1);

			return sSign+addNumber(sNum1, sNum2);
		}

		if((sNum1.charAt(0)!='-')&&(sNum2.charAt(0)=='-')&&(cSign=='+'))
		{
			sNum2=sNum2.substring(1);
			if(sNum1.compareTo(sNum2)==0)
			{
				return "0";
			}else if(compareNumberAsString(sNum1, sNum2))
			{
				return subractNumber(sNum1, sNum2);
			}else if(!(compareNumberAsString(sNum1, sNum2)))
			{
				return sSign + subractNumber(sNum2, sNum1);
			}

		}
		if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)!='-')&&(cSign=='+'))
		{
			sNum1=sNum1.substring(1);
			if(sNum1.compareTo(sNum2)==0)
			{
				return "0";
			}else if(compareNumberAsString(sNum1, sNum2))
			{
				return subractNumber(sNum2, sNum1);

			}else if(!(compareNumberAsString(sNum1, sNum2)))
			{
				return sSign + subractNumber(sNum1, sNum2);
			}

		}

		if((sNum1.charAt(0)!='-')&&(sNum2.charAt(0)!='-')&&(cSign=='+'))
		{
			return addNumber(sNum1, sNum2);
		}


		//Subtraction
		if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)=='-')&&(cSign=='-'))
		{
			sNum1=sNum1.substring(1);
			sNum2=sNum2.substring(1);

			if(sNum1.compareTo(sNum2)==0)
			{
				return "0";
			}else if(compareNumberAsString(sNum1, sNum2))
			{
				return sSign + subractNumber(sNum1, sNum2);

			}else if(!(compareNumberAsString(sNum1, sNum2)))
			{
				return subractNumber(sNum2, sNum1);
			}
		}

		if((sNum1.charAt(0)!='-')&&(sNum2.charAt(0)=='-')&&(cSign=='-'))
		{
			sNum2=sNum2.substring(1);
			return addNumber(sNum1, sNum2);


		}
		if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)!='-')&&(cSign=='-'))
		{
			sNum1=sNum1.substring(1);
			return sSign + addNumber(sNum1, sNum2);

		}

		if((sNum1.charAt(0)!='-')&&(sNum2.charAt(0)!='-')&&(cSign=='-'))
		{

			if(sNum1.compareTo(sNum2)==0)
			{
				return "0";
			}else if(compareNumberAsString(sNum1, sNum2))
			{
				return subractNumber(sNum1, sNum2);

			}else if(!(compareNumberAsString(sNum1, sNum2)))
			{
				return sSign + subractNumber(sNum2, sNum1);
			}
		}


		//Multiplication

		if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)=='-')&&(cSign=='*'))
		{
			sNum1=sNum1.substring(1);
			sNum2=sNum2.substring(1);
			return multiplicationHandler(sNum1, sNum2);

		}else if((sNum1.charAt(0)!='-')&&(sNum2.charAt(0)=='-')&&(cSign=='*'))
		{
			sNum2=sNum2.substring(1);
			return sSign + multiplicationHandler(sNum1, sNum2);

		}else if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)!='-')&&(cSign=='*'))
		{
			sNum1=sNum1.substring(1);
			return sSign + multiplicationHandler(sNum1, sNum2);

		}else if((cSign=='*'))
		{
			return multiplicationHandler(sNum1, sNum2);
		}


		//Division

		if((sNum2.compareTo("0")==0)&&(cSign=='/'))
		{

			return "Infinite";

		}

		if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)=='-')&&(cSign=='/'))
		{
			sNum1=sNum1.substring(1);
			sNum2=sNum2.substring(1);
			return divideNumber(sNum1, sNum2, '/');

		}else if((sNum1.charAt(0)!='-')&&(sNum2.charAt(0)=='-')&&(cSign=='/'))
		{
			sNum2=sNum2.substring(1);
			return sSign + divideNumber(sNum1, sNum2, '/');

		}else if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)!='-')&&(cSign=='/'))
		{
			sNum1=sNum1.substring(1);
			return sSign + divideNumber(sNum1, sNum2, '/');

		}else if((cSign=='/'))
		{
			return divideNumber(sNum1, sNum2, '/');
		}



		//Modulo
				if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)=='-')&&(cSign=='%'))
				{
					sNum1=sNum1.substring(1);
					sNum2=sNum2.substring(1);
					return divideNumber(sNum1, sNum2, '%');

				}else if((sNum1.charAt(0)!='-')&&(sNum2.charAt(0)=='-')&&(cSign=='%'))
				{
					sNum2=sNum2.substring(1);
					return sSign + divideNumber(sNum1, sNum2, '%');

				}else if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)!='-')&&(cSign=='%'))
				{
					//sNum1=sNum1.substring(1);

					//return sSign + divideNumber(sNum1, sNum2, '%');
					return "0";

				}else if((cSign=='%'))
				{
					return divideNumber(sNum1, sNum2, '%');
				}
            //Exponential
            if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)=='-')&&(cSign=='^'))
            {
                return "0";

            }else if((sNum1.charAt(0)!='-')&&(sNum2.charAt(0)=='-')&&(cSign=='^'))
            {
                return "0";

            }else if((sNum1.charAt(0)=='-')&&(sNum2.charAt(0)!='-')&&(cSign=='^'))
            {
                sNum1=sNum1.substring(1);

                int lastDigit=Character.getNumericValue(sNum2.charAt(sNum2.length()-1));
                if((lastDigit&1)==0)
                {
                    return sSign + ExponentialHandler(sNum1, Integer.parseInt(sNum2));
                }else
                {
                    return ExponentialHandler(sNum1, Integer.parseInt(sNum2));

                }

            }else if((cSign=='^'))
            {
                return ExponentialHandler(sNum1, Integer.parseInt(sNum2));
            }

        }
				//Root
				else
				{
                    if(sNum1.charAt(0)=='-')
                    {
                        sNum1=sNum1.substring(1);
                    }
					return doSquareRoot(sNum1);// + rootSign;

				}


		return sResult;

	}

	/**
	 *
	 * @param sNumber
	 * @return Linked list of given string
	 */
	public static LinkedList<Long> frameList(String sNumber)
	{

		int noOfNodes;
		long sCoEff=0;

		//To identify the no of nodes for the linked list . may have an extra node for less than 4 digits
		noOfNodes=sNumber.length()/noOfDigit;

		LinkedList<Long> resList=new LinkedList<Long>();
		for(int i=0;i<noOfNodes;i++)
		{
			sCoEff=Long.parseLong(sNumber.substring((sNumber.length())-noOfDigit, sNumber.length()));
			resList.addLast(sCoEff);
			sNumber=sNumber.substring(0, sNumber.length()-noOfDigit);
		}
		if(sNumber.length()>0)
		{
			sCoEff=Long.parseLong(sNumber);
			resList.addLast(sCoEff);
		}
		return resList;
	}

	/**
	 *
	 * @param sNum1
	 * @param sNum2
	 * @return Addition of two strings
	 */
	public static String addNumber(String sNum1, String sNum2)
	{
		LinkedList<Long> lList1=frameList(sNum1);
		LinkedList<Long> lList2=frameList(sNum2);
		StringBuilder sBuilder=new StringBuilder("");

		long nNum1=0;
		long nNum2=0;
		long nCarry=0;
		int base=nBase;
		long res=0;

		if(lList1==null||lList2==null)
			return null;

		while((lList1.peek()!=null) || (lList2.peek()!=null))
		{

			if((lList1.peek()!=null) && (lList2.peek()!=null))
			{
			 nNum1=lList1.pop();
			 nNum2=lList2.pop();
			 //To add the result set
			res=nCarry+nNum1+nNum2;
			//To make the carry as zero.if carry is not zero new node must be inserted
			nCarry=0;
			nCarry=res/base;
			res=res%base;
			sBuilder.insert(0, addTrailingZeros(res, nBase));//new
			}else{
				LinkedList<Long> contList;
				contList=(lList1.peek()!=null)?lList1:lList2;

				while(contList.peek()!=null)
				{
					res=contList.pop();
					res=res+nCarry;
					nCarry=0;
					nCarry=res/base;
					res=res%base;
					sBuilder.insert(0, addTrailingZeros(res, nBase));//new
				}
			}
		}
		//To insert the last carry of the addition
		if(nCarry!=0)
		{
			sBuilder.insert(0, addTrailingZeros(nCarry, nBase));//new
		}
		return sBuilder.toString().replaceFirst("^0+(?!$)", "");
	}


	/**
	 *
	 * @param sNum1
	 * @param sNum2
	 * @return subtraction of two strings
	 */
	public static String subractNumber(String sNum1, String sNum2)
	{
		int sLen1=sNum1.length();
		int sLen2=sNum2.length();
		/*if(sLen1<sLen2)
		{
			return _sNegative_Error;
		}
		else if(sLen1==sLen2)
		{
			if((sNum1.compareTo(sNum2))<0)
			{
				return _sNegative_Error;
			}
		}*/

		LinkedList<Long> res=frameList(sNum1);
		LinkedList<Long> res1=frameList(sNum2);

		String sRes=subractHandler(res, res1);
		return sRes;
	}


	/**
	 *
	 * @param oList1
	 * @param oList2
	 * @return produced subtracted result set
	 */
	public static String subractHandler(LinkedList<Long> oList1, LinkedList<Long> oList2)
	{
		long nNum1=0;
		long nNum2=0;
		long nCarry=0;
		long resNum=0;
		StringBuilder sBuilder=new StringBuilder("");

		if(oList1==null||oList2==null)
			return null;
		while((oList1.peek()!=null) || (oList2.peek()!=null))
		{
			if((oList1.peek()!=null) && (oList2.peek()!=null))
			{
				nNum1=oList1.pop();
				nNum2=oList2.pop();
			nNum1=nNum1+nCarry;
			nCarry=0;
			if(nNum1<nNum2)
			{
				nNum1=nNum1+nBase;
				nCarry=-1;
			}
			resNum=nNum1-nNum2;
			sBuilder.insert(0, addTrailingZeros(resNum, nBase));//new
		}else
		{

			LinkedList<Long> contList;
			contList=oList1;
			while(contList.peek()!=null)
			{
				resNum=contList.pop();
				resNum=resNum+nCarry;
				nCarry=0;
				sBuilder.insert(0, addTrailingZeros(resNum, nBase));//new
			}
		}
		}
		//It will be useful while we handle the negative numbers
		/*if(nCarry==-1)
		{
			Node negNode=new Node();
			negNode.sCoEff=-1;
			negNode.exp=nExp;	
			oResList.addLast(negNode);

		}*/

    	return sBuilder.toString().replaceFirst("^0+(?!$)", "");
	}


	/**
	 *
	 * @param sNum1
	 * @param sNum2
	 * @return multiplied output string
	 */
	public static String multiplicationHandler(String sNum1, String sNum2)
	{
		LinkedList<Long> oList1=frameList(sNum1);
		LinkedList<Long> oList2=frameList(sNum2);
		String resl=multiplyNumber(oList1, oList2);
		return resl;

	}

	/**
	 *
	 * @param lList1
	 * @param lList2
	 * @return multiply big numbers by taking the input as list
	 */
	public static String multiplyNumber(LinkedList<Long> lList1, LinkedList<Long> lList2)
	{
		int inFirstLeng=lList1.size();
		int inSecondLen=lList2.size();
		long[] nBuffer=new long[(inFirstLeng+inSecondLen)];
		long[] nCarryBuffer=new long[inFirstLeng+inSecondLen];

		long nBuffCoEff=0;
		long nBuffCarry=0;
		long additionCarry=0;
		long resCoEff=0;
		int resExp=0;
		int nExp1=0;
		int nExp2=0;

		StringBuilder sBuilder=new StringBuilder("");
		long nResNum=0;
		if(lList1==null||lList2==null)
			return null;
			for(Long sCoEff1 : lList1)
			{
			for(Long sCoEff2 : lList2)
			{
				resCoEff=sCoEff1*sCoEff2;
				resExp=nExp1+nExp2;
				if(resExp==0)
				{
					nBuffCoEff=resCoEff%nBase;
					nBuffCarry=resCoEff/nBase;

					nBuffer[resExp]=nBuffCoEff;
					nCarryBuffer[resExp]=nBuffCarry;
				}else
				{
					resCoEff=resCoEff+nBuffer[resExp];
					nBuffCoEff=resCoEff%nBase;
					nBuffCarry=resCoEff/nBase;
					nBuffer[resExp]=nBuffCoEff;
					nCarryBuffer[resExp]+=nBuffCarry;
				}

				nExp2++;
			}
			nExp2=0;
			nExp1++;
		}
			for(int i=0;i<nBuffer.length;i++)
			{
				if(i==0)
				{
					sBuilder.insert(0, addTrailingZeros(nBuffer[i], nBase));
				}else
				{
				nResNum=nBuffer[i]+nCarryBuffer[i-1];
				additionCarry=nResNum/nBase;
				nResNum=nResNum%nBase;
				nCarryBuffer[i]+=additionCarry;
				sBuilder.insert(0, addTrailingZeros(nResNum, nBase));
				}
			}
			return sBuilder.toString().replaceFirst("^0+(?!$)", "");
	}

	/**
	 *
	 * @param sNum1
	 * @param sNum2
	 * @param identifier
	 * @return Quotient or the reminder
	 */
	public static String divideNumber(String sNum1, String sNum2, char identifier)
	{

		String sQuotient="";
		String sTempQuotient="";
		String sReminder="";
		int sLen1=sNum1.length();
		int sLen2=sNum2.length();
		String[] sProducedQuotDivi=new String[2];

		String sTempNo=sNum2;
		if(sLen1<sLen2)
		{
			return "0";
		}
		else if(sLen1==sLen2)
		{
		if(!compareNumberAsString(sNum1, sNum2))
			{
				return "0";
			}
		}

		//System.out.println("Temp Num : "+sTempNo);
		//System.out.println("Quotient Num : "+sQuotient);

		sTempQuotient="0";
		sProducedQuotDivi=frameDivisorWithZero(sNum1, sNum2);
		sQuotient=sProducedQuotDivi[0];
		sTempNo=sProducedQuotDivi[1];

		while((compareNumberAsString(sNum1, sTempNo)))
		{
			//System.out.println("test 1");

			/*if(sLen2<sLen1-1)
			{
				sProducedQuotDivi=frameDivisorWithZero(sNum1, sNum2);
				sQuotient=sProducedQuotDivi[0];
				sTempNo=sProducedQuotDivi[1];
				
			}*/



			LinkedList<Long> oList1=frameList(sNum1);
			LinkedList<Long> oList2=frameList(sTempNo);
			sNum1=subractHandler(oList1, oList2);
			sLen1=sNum1.length();

			sTempQuotient=addNumber(sTempQuotient.toString(), sQuotient.toString());

			if(!(compareNumberAsString(sNum1, sTempNo)))
			{
				sProducedQuotDivi=frameDivisorWithZero(sNum1, sNum2);
				sQuotient=sProducedQuotDivi[0];
				sTempNo=sProducedQuotDivi[1];

			}

		}

		if(compareNumberAsString(sNum1, sNum2))
		{
			sNum1=subractNumber(sNum1, sNum2);
			sTempQuotient=addNumber(sTempQuotient.toString(), "1");
		}

		if(identifier=='%')
		{
			return sNum1;
		}else
		{
			return sTempQuotient.replaceFirst("^0+(?!$)", "");
		}
	}


	/**
	 *
	 * @param sNum1
	 * @param sNum2
	 * @return add zeros at the tail of num2 and make equivalent the no of digits in num1
	 */
	public static String[] frameDivisorWithZero(String sNum1, String sNum2)
	{
		int sLen1=sNum1.length();
		int sLen2=sNum2.length();
		int count=0;
		StringBuffer sQuotient=new StringBuffer("1");
		StringBuffer sTempNoBuffer=new StringBuffer(sNum2);
		String[] sQuotientDivisor=new String[2];

		while((sLen2<sLen1)&&(compareNumberAsString(sNum1, sTempNoBuffer.toString())))
		{
			count++;
			sTempNoBuffer.append("0");
			sQuotient.append("0");
			sLen2=sTempNoBuffer.length();
		}

		if((count>0)&&(!(compareNumberAsString(sNum1, sTempNoBuffer.toString()))))
		{
			sQuotient=sQuotient.deleteCharAt(sQuotient.length()-1);
			sTempNoBuffer=sTempNoBuffer.deleteCharAt(sTempNoBuffer.length()-1);
		}
		sQuotientDivisor[0]=sQuotient.toString();
		sQuotientDivisor[1]=sTempNoBuffer.toString();

		return sQuotientDivisor;

	}

	/**
	 *
	 * @param sNum
	 * @return square root value
	 */

	public static String doSquareRoot(String sNum)
	{
		String sPredicted;
		String sNewPredicted;
		/*if(sNum!="")
		{
			char cLast=sNum.charAt(sNum.length()-1);
			
		}
		StringBuffer stringBuffer=new StringBuffer(sNum);*/

		sNewPredicted = subractNumber(sNum, "1");
		sPredicted = sNum;
		for(long i =0;(!(sPredicted.compareTo(sNewPredicted)==0)) && (i !=1000000000000000000L);i++)
		{

			sPredicted = sNewPredicted;
			sNewPredicted = subractNumber(sPredicted,   divideNumber(subractNumber(multiplicationHandler(sPredicted, sPredicted), sNum), multiplicationHandler("2", sPredicted), '/'));
		}

		//System.out.println(newEstimate);
		return subractNumber(sNewPredicted, "1");
	}

	/**
	 *
	 * @param nNum
	 * @param nBase
	 * @return add zero to the no based on the base chosen
	 */

	public static String addTrailingZeros(long nNum, int nBase)
	{
			int noOfDigit=(int) Math.log10(nBase);
			String formatted="%0"+noOfDigit+"d";
		    String resData=String.format(formatted, nNum);

		    return resData;

	}

	/**
	 *
	 * Returns false when num1 is less than num2
	 */
	public static boolean compareNumberAsString(String sNum1, String sNum2)
	{
		 int sLen1=sNum1.length();
	      int sLen2=sNum2.length();
	      boolean prevBit=false;
	      if(sLen1<sLen2)
	      {
	    	  return false;
	      }else if(sLen1==sLen2)
	      {
	    	int i=0;
	    	while(i<sLen1)
	    	{
	    		if((!prevBit)&&(sNum1.charAt(i)>sNum2.charAt(i)))
	    		{
	    			prevBit=true;
	    		}

	    		if((sNum1.charAt(i)<sNum2.charAt(i)))
	    		{
	    			if(!prevBit)
	    			{
		  	    	  return false;
	    			}
	    		}
	    		i++;
	    	}

	      }
    	  return true;

	}

	/**
	 *
	 * @param m
	 * @param n
	 * @return returns the exponential result set
	 */
	public static String ExponentialHandler(String m, int n)
	{
		hDynamicMap = new HashMap<Integer, String>();
        hDynamicMap.put(1, m);
		String res=mPowerN(m, n);
		return res;
	}

	/**
	 *
	 * @param m
	 * @param n
	 * @return calculate m power n value
	 */
	public static String mPowerN(String m, int n)
	{
		String mapEntry;

		if(     ( mapEntry = hDynamicMap.get(n)   )!=null )
		{
			return mapEntry;
		}
		if(n==0)
			return m;

		if((n&1)==0)
		{
			hDynamicMap.put(n, multiplicationHandler(mPowerN(m, n/2), mPowerN(m, n/2)));
		}else
		{
			hDynamicMap.put(n, multiplicationHandler(m, multiplicationHandler(mPowerN(m, n/2), mPowerN(m, n/2))));

		}
		return hDynamicMap.get(n);

	}
}
