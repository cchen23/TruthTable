package core;

public class Generator {
	
	//returns string name of state encoding
	private String getEncoding(int s1, int s0) {
		switch (s1) {
		case 0:
			switch (s0) {
			case 0:
				return "init";
			case 1:
				return "LT";
			}
		case 1:
			switch (s0) {
			case 0:
				return "GT";
			case 1:
				return "Done";
			}
		}
		return "Invalid state";
	}
	
	private int getD(int s0, int s1) {
		if (s1 == 1 && s0 == 1) return 1;
		return 0;
	}
	
	private int getSel1(int s0, int s1) {
		if (s1 == 1 && s0 == 0) return 1;
		return 0;
	}
	
	private int getSel0(int s0, int s1) {
		if (s1 == 0 && s0 == 1) return 1;
		return 0;
	}
	
	private int getN1(int gt, int eq) {
		if (gt == 1 || eq == 1) return 1;
		return 0;
	}
	
	private int getN0(int gt, int eq, int s0, int s1) {
		if (eq == 0 && gt == 0 || eq == 1) return 1;
		return 0;
	}
	
	public static void main(String[] args) {
		Generator g = new Generator();
		for (int s1 = 0; s1 < 2; s1++) {
			for (int s0 = 0; s0 < 2; s0++) {
				for (int eq = 0; eq < 2; eq++) {
					for (int gt = 0; gt < 2; gt++) {
						int d = g.getD(s0, s1);
						int sel1 = g.getSel1(s0, s1);
						int sel0 = g.getSel0(s0, s1);
						int n1 = g.getN1(gt, eq);
						int n0 = g.getN0(gt, eq, s0, s1);
						System.out.println(eq + "," + gt + "," + s1 + "," + s0
								+ "," + g.getEncoding(s1, s0) + "," + d + ","
								+ sel1 + "," + sel0 + "," + n1 + "," + n0 + ","
								+ g.getEncoding(n1, n0));
					}
				}
			}
		}
	}
}
