package inconsistency;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static Inconsistency[] inconsistencies = {
			new Inconsistency(1, 2, 4, 4),
			new Inconsistency(1, 4, 4, 4),
			new Inconsistency(2, 2, 4, 4),
			new Inconsistency(2, 3, 4, 4),
			new Inconsistency(2, 3, 4, 2),
			new Inconsistency(2, 4, 4, 3),
			new Inconsistency(3, 1, 4, 3),
			new Inconsistency(3, 1, 4, 4),
			new Inconsistency(2, 2, 5, 1),
			new Inconsistency(4, 2, 5, 1),
			new Inconsistency(4, 2, 7, 2),
			new Inconsistency(4, 4, 7, 1),
			new Inconsistency(5, 1, 8, 1),
			new Inconsistency(5, 3, 8, 1),
			new Inconsistency(5, 1, 8, 3),
			new Inconsistency(5, 3, 8, 3),
			new Inconsistency(5, 1, 8, 4)
	};
	
	public static List<Design> inconsistents = new ArrayList<Design>();
	public static List<Inconsistency> duplicates;
	
	public static void main(String[] args) {
		int inconsistencyCount = 0;
		int designCount = 0;
		for (int system1 = 1; system1 <= 4; system1++) {
			for (int system2 = 1; system2 <= 4; system2++) {
				for (int system3 = 1; system3 <= 3; system3++) {
					for (int system4 = 1; system4 <= 4; system4++) {
						for (int system5 = 1; system5 <= 3; system5++) {
							for (int system6 = 1; system6 <= 3; system6++) {
								for (int system7 = 1; system7 <= 3; system7++) {
									for (int system8 = 1; system8 <= 4; system8++) {
										Design d = new Design(system1, system2, system3, system4, system5, system6, system7, system8);
										if (d.hasInconsistency()) {
											inconsistencyCount++;
											if (inconsistents.contains(d)) throw new RuntimeException();
											inconsistents.add(d);
											//System.out.println(d);
										}
										designCount++;
									}
								}
							}
						}
					}
				}
			}
		}
		System.out.println(inconsistencyCount);
		System.out.println(designCount);
	}
	
	public static class Inconsistency {
		public int system1, embodiment1, system2, embodiment2;
		public Inconsistency(int system1, int embodiment1, int system2, int embodiment2) {
			this.system1 = system1;
			this.embodiment1 = embodiment1;
			this.system2 = system2;
			this.embodiment2 = embodiment2;
		}
	}
	
	public static class Design {
		public int a, b, c, d, e, f, g, h;
		public Design(int a, int b, int c, int d, int e, int f, int g, int h) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
			this.e = e;
			this.f = f;
			this.g = g;
			this.h = h;
		}
		
		public int getEmbodiment(int system) {
			switch (system) {
			case 1: return a;
			case 2: return b;
			case 3: return c;
			case 4: return d;
			case 5: return e;
			case 6: return f;
			case 7: return g;
			case 8: return h;
			default: throw new RuntimeException();
			}
		}
		
		public boolean hasInconsistency() {
			for (int i = 0; i < inconsistencies.length; i++) {
				Inconsistency inc = inconsistencies[i];
				if (inc.embodiment1 == getEmbodiment(inc.system1) && inc.embodiment2 == getEmbodiment(inc.system2)) {
					//System.out.printf("System 1: %s\tEmbodiment 1: %s\tSystem 2: %s\tEmbodiment 2: %s\n", inc.system1, inc.embodiment1, inc.system2, inc.embodiment2);
					return true;
				}
			}
			return false;
		}
		
		public String toString() {
			return String.format("System 1: %s\tSystem 2: %s\tSystem 3: %s\tSystem 4: %s", a, b, c, d);
		}
		
		public boolean equals(Object o) {
			if (!(o instanceof Design)) return false;
			Design de = (Design) o;
			return a == de.a && b == de.b && c == de.c && d == de.d && e == de.e && de.f == f && de.g == g && de.h == h;
		}
	}
}
