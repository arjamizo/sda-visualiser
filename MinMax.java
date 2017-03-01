package sda;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinMax {
	
	// trick znaleziony po raz pierwszy w JDownloader
	static {
		// dodaj do VM arguments "-enableassertions" w ustawieniach 
		// projektu lub startowania programu
		assert powitanie("Jan").equals("Czesc Jan!"): "niepoprawny format"; 
		System.out.println(powitanie("Wam"));
	}
	
	public static String powitanie(String imie) {
		return "Czesc "+imie+"!";
	}
	
	public int max(int a, int b) {
		if(a>b) {
			return a;
		} else {
			return b;
		}
	}
	
	static {
		// Liczby bedące wielokrotnością 3 lub 5
		// mniejsze niż 10, to: 0, 3, 5, 6, 9
		// ich suma to 8+15=23
		assert sumaWielokrotnosci3oraz5(1, 10) == 23;
		// Ile wynosi suma takich liczb <1000?
		System.out.println("Suma liczb <1000 takich, ze n%3==n%5==0 to " 
				+ sumaWielokrotnosci3oraz5(1, 1000));
	}
	//	https://projecteuler.net/problem=1
	public static int sumaWielokrotnosci3oraz5(int from, int below) {
		int sum = 0;
		for (int n = from; n < below; n++) {
			if ( n % 3 == 0 || n % 5 == 0) {
				sum = sum + n; 
			}
		}
		return sum;
	}
	
	static {
		// Liczby fibonnaciego to a_n=a_{n-1}+a_{n-2}, 
		// czyli: 1+1=1+2=2+3=3+5=8 itd. ...
		assert fibb(1) == 1 && fibb_if(1) == 1;
		assert fibb(2) == 1 && fibb_if(2) == 1;
		assert fibb(3) == 2;
		assert fibb(4) == 3 && fibb_if(4) == 3;
		assert fibb(5) == 5;
		assert fibb(6) == 8 && fibb_if(6) == 8;
		System.out.println("12 liczba Fibbonaciego to " + fibb(12));
	}
	
	public static int fibb_if(int n) {
		if (n<=2) 
			return 1; 
		else {
			return fibb(n-1)+fibb(n-2);
		}
	}

	public static int fibb(int n) {
		return n<=2?1:fibb(n-1)+fibb(n-2);
	}
	
	static {
		System.out.println(fibb_iter(12));
		assert fibb_iter(12) == 144;
	}
	
	public static long fibb_iter(int n) {
		long a = 1, b = 1, c = 2;
		for (int i = 0; i < n - 1; ++i) {
			c = a + b;
			a = b;
			b = c;
		}
		return a;
	}
	
	static {
		System.out.println("parz fibb <1e6=" + sumaParzystychLiczbFibbPonizej4Mln());
	}
	public static long sumaParzystychLiczbFibbPonizej4Mln() {
		long sum = 0, n = 0; 
		int i = 1;
		do {
			n = fibb_iter(i);
			i++;
			if (n % 2 == 0) {
				sum += n;
			}
		} while ( n < 4000000);
		return sum;
	}

	/**
	 * Znajdz zmienną z większą wartością
	 * bardzo podobne do implementacji w samym java-core
	 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/7u40-b43/java/lang/Math.java#Math.max%28int%2Cint%29
	 */
	public static int max_TerOp(int a, int b) {
		return a>b?a:b;
	}
	
	public static int min_TerOp(int a, int b) {
		return a>b?a:b;
	}

	static {
		System.out.println(max_list(new int[] {1, 2, 3, 5, 4, 3}));
	}
	/**
	 * Znajdź największy element z listy.
	 * Jaka optymalizacja?
	 */
	public static int max_list(int[] ar) {
		int max = ar[0];
		for (int e : ar) {
			max=max>e?max:e;
		}
		return max;
	}

//	public int max_recur(int[] ar, int max) {
//		return max_recur(Arrays.asList(ar).subList(1, ar.length-1), max);
//	}

	static {
//		sysout[ctrl+space]
		System.out.println(max_recur(Arrays.asList(new Integer[] {13, 314, 1337, 271}), -1));
		assert max_recur(Arrays.asList(new Integer[] {13, 314, 1337, 271}), -1) == 1337;
	}
	public static int max_recur(List<Integer> ar, int max) {
//		System.out.println(ar);
//		System.out.println(max);
//		System.out.println("---");
		if(ar.size()>0) {
			List<Integer> rest = ar.subList(1, ar.size()); // odcina pierwszy element, po pierwszym wykonaniu: {314, 1337, 271}
			Integer currentMax = Math.max(max, ar.get(0)); // przy pierwszym wykonaniu max=-1, ...get(0)=13
			return max_recur(rest, currentMax);
		} else {
			return max;
		}
	}
	
	static {
		assert max_recur(Arrays.asList(new Integer[] {13, 314, 1337, 271}), 0, -1) == 1337;
		assert max_recur(Arrays.asList(new Integer[] {1, 2, 3}), 0, -1) == 3;
	}
	
	public static int max_recur(List<Integer> ar, int start, int max) {
		return start==ar.size()?max:max_recur(ar, start+1, Math.max(max, ar.get(start)));
	}
	
	/**
	 * Ze względu na to, że tablicę możemy zapisać 
	 * jako Integer[] oraz List<Integer>
	 * potrzebujemy uwzględnić oba przypadki 
	 */
	public static int max_recur(Integer[] ar, int max) {
		return max_recur(Arrays.asList(ar), max);
	}

	/**
	 * By nie podawać drugiego argumentu, przyda się też 
	 * domyślny argument, który ustawiony na najmniejszą 
	 * możliwową wartość zawsze spełni swoje zadanie 
	 */
	public static int max_recur(Integer[] ar) {
		return max_recur(ar, Integer.MIN_VALUE);
	}
	
	/**
	 * Domyślny argument z wariantem List<Integer> 
	 */
	public static int max_recur(List<Integer> ar) {
		return max_recur(ar, Integer.MIN_VALUE);
	}
	
	/**
	 * Gdzie jest błąd? Jaka jeszcze możnaby przeprowadzić optymalizację?
	 */
	public int min_own (int[] ar) {
		int min=ar[0];
		for (int e : ar) {
			min=min>e?min:e;
		}
		return min;
	}
	
	/**
	 * Rozwiązanie uwzględniające funkcje istniejące w języku. 
	 */
	public Integer max(Integer[] ar) {
		return Collections.max(Arrays.asList(ar));
	}

	public static void main(String[] args) {
		System.out.println("Koniec.");
	}
	

	static {
		// https://projecteuler.net/problem=3
		assert najwiekszyDzielnikLiczby(13195) == 29;
		System.out.println("naj dzielnik "+najwiekszyDzielnikLiczby(600851475143l));
	}
	
	static {
	    assert !isPrime(1);
	    assert isPrime(2);
	    assert isPrime(3);
	    assert !isPrime(9);
	    assert isPrime(13);
	    assert isPrime(17);
	    assert !isPrime(14);
	    assert !isPrime(22);
	    assert isPrime(653);
	}
	

	private static boolean isPrime2(long num) {
		if (num == 1) return false;
        if (num == 2) {
        	return true;
        }
        for (int i = 3; i < num; i++) {
            if (num % i == 0) {
            	return false;
            }
        }
        return true;
	}
	
	private static boolean isPrime(long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
	}
	

	static {
		assert nthPrime(6)==13;
		System.out.println(nthPrime(10001));
	}
	
	private static int nthPrime(int n) {
		int i = 2, cnt = 1;
		while(cnt!=n) {
			if(isPrime(++i)) cnt++;
		}
		return i;
	}
	
	public static long najwiekszyDzielnikLiczby(long n) {
		long limit = (long) Math.sqrt(n);
		for (long i = limit; i>=1; --i) {
			if(n % i == 0 && isPrime(i)) {
				return i;
			}
		}
		return -1; // jesli blad
	}
	
	static {
		System.out.println("najpalindrom" + najdluzszaLibczaPalindrom6znakow());
	}

	public static int najdluzszaLibczaPalindrom6znakow() {
		int max = 0;
		for (int i = 100; i<1000; ++i) {
			for (int j = 100; j<1000; ++j) {
			String s = Integer.toString(i*j);
			if (s.length()!=6) continue; 
			if(s.charAt(0) == s.charAt(5))
				if (s.charAt(1) == s.charAt(4)) 
					if (s.charAt(2) == s.charAt(3)) 
						max = Math.max(i*j, max);
			}
		}
		return max;
	}
	

	public static int najdluzszaLibczaPalindrom6znakow_int() {
		int max = 0;
		for (int i = 100; i<1000; ++i) {
			for (int j = 100; j<1000; ++j) {
				int n = i*j;
				
			}
		}
		return max;
	}
	
	static {
		int val = minDivByAll_smart(1, 10);
		assert val == 2520;
//		assert minDivByAll(1, 10) == val;
		assert minDivByAll_bf(1, 10) == 2520;
		System.out.println(minDivByAll_smart(1, 20));
	}
	
	private static boolean isDivByAll(int num, int b, int e) {
		for (int i = b; i <= e; i++) {
			if (num % i != 0) return false;
		}
		return true;
	}

	private static int minDivByAll(int begin, int end) {
		int num = 1;
		while(!isDivByAll(num, begin, end)) {
			num += 1;
		}
		return num;
	}
	
	private static int minDivByAll_bf(int begin, int end) {
		int n = 1;
		while(n%2+n%3+n%4+n%5+n%6+n%7+n%8+n%9+n%10+
				(end!=20?0:n%11+n%12+n%13+n%14+n%15+n%16+n%17+n%18+n%19+n%20)
				!=0) {
			n += 1;
		}
		return n;
	}
	
	static int gcd(int a,int b) { // NWD
	    if(b>a) return gcd(b,a);
	    if(b==0)
	    return a;
	    return gcd(b,a%b);
	}
	static int lcm(int a,int b) { // NWW
	    int k=gcd(a,b);
	    return a/k*b;
	}

	private static int minDivByAll_smart(int begin, int end) { // NWW (N liczb)
		int num = 1;
		for(int i=end; i>begin; --i) {
			if(num % i != 0) {
				num = lcm(num, i);
			}
		}
		return num;
	}
	
	static {
		assert sumSqureDiff(1, 10) == 2640;
		System.out.println(sumSqureDiff(1, 100));
	}
	
	private static int sumSqureDiff(int a, int b) {
		int sum=0, squares=0;
		for (int i = a; i <= b; i++) {
			sum += i;
		}
		for (int i = a; i <= b; i++) {
			squares += i*i;
		}
		return Math.abs(sum*sum-squares);
	}

	static {

		System.out.println("sum of squares - square of sum = " + sumSqureDiff(1, 2));
		assert sumSqureDiff(1, 2) == 4;
		assert sumSqureDiff(1, 10) == 2640;
		assert sumSqureDiff_hardcoded() == sumSqureDiff(1, 100);
		System.out.println(sumSqureDiff(1, 100));
		
	}
	private static int sumSqureDiff_hardcoded() {
		// via https://projecteuler.net/quote_post=242026-YB8vDmNow49ikrR2
		return ((100*(100+1)/2)*(100*(100+1)/2)- 100*(100+1)*(2*100+1)/6);
	}
	
	// R: (sum(c(1:100)^2) - sum(1:100)^2)

	
	static {
		String str = "";
		assert prodAdjacetDigits(4, str="73167176531330624919225119674426574742355349194934"+
				"96983520312774506326239578318016984801869478851843"+
				"85861560789112949495459501737958331952853208805511"+
				"12540698747158523863050715693290963295227443043557"+
				"66896648950445244523161731856403098711121722383113"+
				"62229893423380308135336276614282806444486645238749"+
				"30358907296290491560440772390713810515859307960866"+
				"70172427121883998797908792274921901699720888093776"+
				"65727333001053367881220235421809751254540594752243"+
				"52584907711670556013604839586446706324415722155397"+
				"53697817977846174064955149290862569321978468622482"+
				"83972241375657056057490261407972968652414535100474"+
				"82166370484403199890008895243450658541227588666881"+
				"16427171479924442928230863465674813919123162824586"+
				"17866458359124566529476545682848912883142607690042"+
				"24219022671055626321111109370544217506941658960408"+
				"07198403850962455444362981230987879927244284909188"+
				"84580156166097919133875499200524063689912560717606"+
				"05886116467109405077541002256983155200055935729725"+
				"71636269561882670428252483600823257530420752963450") == 9*9*8*9;
		System.out.println(prodAdjacetDigits(13, str));
	}

	// potential problems: int vs long
	private static long prodAdjacetDigits(int n, String string) {
		long max = Integer.MIN_VALUE;
		for (int i = 0; i < string.length()-n+1; i++) {
			String[] split = string.substring(i, i+n).split("");
//			System.out.println(Arrays.toString(split));
			long val = 1;
			for (int j = 1; j < split.length; j++) {
				val *= Integer.parseInt(split[j]);
			}
//			val *= 
//					Integer.parseInt(split[1]) *
//					Integer.parseInt(split[2]) * 
//					Integer.parseInt(split[3]) * 
//					Integer.parseInt(split[4]);
			max = Math.max(max, val);
		}
		return max;
	}
	
	static {
		System.out.println("pita"+pith(1000));
	}

	private static int pith(int n) {
		int a=0,b=0,c=0;
		outerloop:
		for (a = 1; a < n; a++) {
			for (b = 1; b < n; b++) {
				for (c = 1; c < n; c++) {
					if (a+b+c==1000 && a*a+b*b==c*c) break outerloop;
				}
			}
		}
		return a*b*c;
	}
	
	static {
		assert primesBelow(10)==17; // click on method and ctrl+1, then create method
//		System.out.println(primesBelow((int) 2e6));
	}
	
	private static long primesBelow(int n) {
		long sum = 0;
		for (long i = 1; i <= n; i++) {
			if(isPrime(i)) {
				sum += i;
			}
		}
		return sum;
	}
	
	static {
		assert Integer.parseInt("012")==12;
		List<String> lines = null;
		try {
			lines = java.nio.file.Files.readAllLines(
					java.nio.file.Paths.get("./grid20x20.txt"), 
					Charset.defaultCharset());
			System.out.println(Arrays.toString(lines.toArray()));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		assert (maxInGrid(lines, 4, new int[] {1, 1}) == 26*63*78*14); // an example data!
//		assert (maxInGrid(lines, 4, new int[] {1, 1}) == 1788696); // an example!
		System.out.println(maxInGrid(lines, 4, new int[] {-1, -1, -1, 1, 1, 0, 0, 1}));
	}

	private static int maxInGrid(List<String> lines, int steps, int[] dirs) {
		assert dirs.length % 2 == 0;
		int grid[][] = listOfStringsTo2dArr(lines);
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) { // alt+shift+up; alt+shift+e: rename variable
				for (int d = 0; d < dirs.length; d+=2) {
					int prod = 1;
					List<Integer> muls = new ArrayList();
					for (int k = 0; k < 4; k++) {

						int x = i+dirs[d]*k;
						int y = j+dirs[d+1]*k;
						if(!(x>=0 && x<grid.length && y>=0 && y<grid[i].length)) 
							break;
						muls.add(grid[x][y]);
						prod *= grid[x][y];
					}
					if(muls.size()==steps) {
//						System.out.println(Arrays.toString(muls.toArray()) + " = " + prod);
						max = Math.max(max, prod);
					}
				}
			}
		}
		return max;
	}

	private static int[][] listOfStringsTo2dArr(List<String> lines) {
		int ret[][] = new int[lines.size()][];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = new int[lines.get(0).split(" ").length]; // opt 1?
			int index = 0;
			for (String nr : lines.get(i).split(" ")) {
				ret[i][index] = Integer.parseInt(nr);
				index++;
			}
		}
		return ret;
	}
	
	static {
		assert triangleNr(7)==28;
		int n = 1;
		while((dividors(triangleNr(n)))<500) {
			n++;
		}
//		System.out.println(Arrays.toString(set.toArray()));
		System.out.println("first triangle nr with more than 500 dividors: "+triangleNr(n));
	}

	private static int triangleNr(int n) {
		return (n+1)*n/2;
	}

	private static int dividors(int n) {
//		Set<Integer> divs = new HashSet<Integer>();
		int dividors = 0;
		for (int i = 1; i <= n; i++) {
			if(n % i == 0) dividors++; 
//				divs.add(i);
		}
		return dividors;
	}
	
	
}
