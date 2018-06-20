package codecheck;

import java.util.ArrayList;

public class App {
	public static void main(String[] args) {
		int[] mpSet = new int[100];
		int[] combatSet = new int[100];
		ArrayList<TotalCombat> totalCombatList = new ArrayList<TotalCombat>();
		int cardMax = 0;
		int mp = 0;
		int temp = 0;
		int cardnum = 0;

		String ar[] = args[0].split(" ");

		for (int i = 0, l = ar.length; i < l; i++) {
			temp = 0;
			temp = Integer.parseInt(ar[i]);

			if (i == 0) {
				cardMax = temp;
			} else if (i == 1) {
				mp = temp;
			} else if (i % 2 == 1) {
				mpSet[(i - 2) / 2] = temp;
			} else {
				combatSet[(i - 1) / 2] = temp;
				cardnum = (i - 1) / 2 + 1;
			}
		}

		TotalCombat hoge = new TotalCombat();
		totalCombatList.add(hoge);
		TotalCombat hoge2 = new TotalCombat();
		hoge2.combat = hoge2.combat + combatSet[0];
		hoge2.mp = hoge2.mp + mpSet[0];
		hoge2.num = hoge2.num + 1;
		totalCombatList.add(hoge2);
		for (int i = 1; i < cardnum; i++) {
			ArrayList<TotalCombat> tempTotalCombatList = new ArrayList<TotalCombat>(totalCombatList);
			totalCombatList.clear();
			for (TotalCombat o : tempTotalCombatList) {
				totalCombatList.add(o);

				if (o.num < cardMax) {
					TotalCombat hoge3 = new TotalCombat();
					hoge3.combat = o.combat + combatSet[i];
					hoge3.mp = o.mp + mpSet[i];
					hoge3.num = o.num + 1;
					totalCombatList.add(hoge3);
				}
			}
		}

		long maxCombat = 0;
		for (TotalCombat o : totalCombatList) {
			if (o.combat > maxCombat && o.num <= cardMax && o.mp <= mp) {
				maxCombat = o.combat;
			}
		}
		System.out.println(maxCombat);
	}

	public static class TotalCombat {
		long mp;
		long combat;
		long num;

		TotalCombat() {
			mp = 0;
			combat = 0;
			num = 0;
		}
	}
}
