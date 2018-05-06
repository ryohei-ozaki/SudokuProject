import java.lang.Math ;
import java.util.Random ;
import java.io.*;

public class Sudoku_ver_1_0_1 {

	public static void main(String[] args){

		int i = 0;
		String filename = "question01.csv";
		int question[][] = readFile(filename);

		long start = System.currentTimeMillis();

		answer(question);

		long stop = System.currentTimeMillis();
		System.out.print("\n" + (stop - start) + "\n");


	}

	public static void answer(int question[][]){
		int i = 1;

		while(i != 0){
			i = pattern1(question);
		}
	}

	public static int pattern1(int[][] question){

		int i, j, k, m, count1 = 0, count2 = 0;
		int yoko[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
//		int tate[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
//		int block[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
//		int[] Array;
//		Array = new int[9];

		for (i = 0; i < 9; i++){
			if (yoko[i] == 1) continue;
			for (j = 0; j < 9; j++){
				if (question[i][j] != 0) continue;
				int Array[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};

				for (k = 0; k < 9; k++){	//横に入っている数字を確認してリストを1にする
					count2 = 0;
					if (question[i][k] != 0){
						Array[question[i][k] - 1] = 1;
					}
					if (question[i][k] != 0){	//横にすべての数字が入っている場合にフラグを立てる
						count2++;
					}
					if (count2 == 9) {
						yoko[i] = 1;
					}
				}

				for (k = 0; k < 9; k++){	//縦に入っている数字を確認してリストを1にする
					if (question[k][j] != 0){
						Array[question[k][j] - 1] = 1;
					}
				}

//ブロックの中での位置を確認して、左上から順に数字を確認してリストを1にする
				int a = i % 3;
				int b = j % 3;

				a = i - a;
				b = j - b;

/*				if (a == 1){
					a = i - 1;
				} else if (a == 2){
					a = i - 2;
				}

				if (b == 1){
					b = j - 1;
				} else if (b == 2){
					b = j - 2;
				}
*/
				for (k = 0; k < 3; k++){
					for (m = 0; m < 3; m++){
//System.out.println(a + " , " + k);
//System.out.println(b + " , " + m);
						if (question[a + k][b + m] != 0){
							Array[question[a + k][b + m] - 1] = 1;
						}
					}
				}
for(int c = 0; c < 9; c++){
	System.out.print(Array[c]);
}
System.out.println();
//リストを確認して1になっていない数字が1つだけであればその数字をマスに入れる
				count2 = 0;
				for (k = 0; k < 9; k++){
					if (Array[k] == 0){
						count2++;
						a = k + 1;
					}
				}
				if (count2 == 1){
					question[i][j] = a;
					count1++;
				}
			}
		}

		for(i = 0; i < 9; i++){
			for(j = 0; j < 9; j++){
				System.out.print(question[i][j]);
			}
			System.out.println();
		}
		System.out.println();

		return count1;
	}



	public static int[][] readFile(String filename){

		int i, j = 0, k;
		String[] questionStr = new String[9];
		int[][] question;
		question = new int[9][9];

		try {
			FileReader fr = new FileReader("C:\\Users\\roman\\Desktop\\question\\" + filename);
			BufferedReader br = new BufferedReader(fr);

//読み込んだファイルを１行ずつ処理する
			String line;
			while ((line = br.readLine()) != null) {
//区切り文字","で分割する
				questionStr = line.split(",", -1);
				for (i = 0; i < 9; i++){
					k = Integer.parseInt(questionStr[i]);
					question[j][i] = k;
				}
				j++;
			}

for(i = 0; i < 9; i++){
	for(j = 0; j < 9; j++){
		System.out.print(question[i][j]);
	}
	System.out.println();
}
System.out.println();

//終了処理
			br.close();

		} catch (IOException ex) {
//例外発生時処理
			ex.printStackTrace();
		}
		return question;
	}
}

