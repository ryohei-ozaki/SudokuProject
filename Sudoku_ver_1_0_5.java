//予約の考え方を追加

import java.lang.Math ;
import java.util.Random ;
import java.io.*;

public class Sudoku_ver_1_0_5 {

	static int question[][] = new int[9][9];
    static int Array[][][] = new int[9][9][9];

	public static void main(String[] args){
/*
		int num;
		double time = 0;
		for(num = 0; num < 1000; num++){
*/
		int i, j, k;
		String filename = "question02.csv";
		question = readFile(filename);	//問題読み込み
//Array初期化
		for(i = 0; i < 9; i++){
			for(j = 0; j < 9; j++){
				for(k = 0; k < 9; k++){
					Array[i][j][k] = 0;
				}
			}
		}

		long start = System.currentTimeMillis();

		answer(question);

		long stop = System.currentTimeMillis();
		System.out.println("\n" + (stop - start) + "\n");

/*		for(i = 0; i < 9; i++){
			for(j = 0; j < 9; j++){
				for(k = 0; k < 9; k++){
					System.out.print(Array[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
*/
/*
		time += (stop - start);
		}
		System.out.print(time);
*/
	}



	public static void answer(int question[][]){
		int a, b, i = 1, i1 = 0, i2 = 0, i3 = 0;

		while(i != 0){
			i1 = pattern1(question);
			i2 = pattern2(question);
			i3 = pattern3();
			i = i1 + i2 + i3;
		}

		for(a = 0; a < 9; a++){
			for(b = 0; b < 9; b++){
				System.out.print(question[a][b]);
			}
			System.out.println();
		}
		System.out.println();

	}



	public static int pattern1(int[][] question){
System.out.println("pattern1");
		int i, j, k, m, count1 = 0, count2 = 0;
		int yoko[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};

		for (i = 0; i < 9; i++){
			if (yoko[i] == 1) continue;
			for (j = 0; j < 9; j++){
				if (question[i][j] != 0){
					for(k = 0; k < 9; k++){
						Array[i][j][k] = 1;
					}
					continue;
				}

				for (k = 0; k < 9; k++){	//横に入っている数字を確認してリストを1にする
					count2 = 0;
					if (question[i][k] != 0){
						Array[i][j][question[i][k] - 1] = 1;
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
						Array[i][j][question[k][j] - 1] = 1;
					}
				}

//ブロックの中での位置を確認して、左上から順に数字を確認してリストを1にする
				int a = i % 3;
				int b = j % 3;

				a = i - a;
				b = j - b;

				for (k = 0; k < 3; k++){
					for (m = 0; m < 3; m++){
//System.out.println(a + " , " + k);
//System.out.println(b + " , " + m);
						if (question[a + k][b + m] != 0){
							Array[i][j][question[a + k][b + m] - 1] = 1;
						}
					}
				}
//for(int c = 0; c < 9; c++){
//	System.out.print(Array[c]);
//}
//System.out.println();
//リストを確認して1になっていない数字が1つだけであればその数字をマスに入れる
				count2 = 0;
				for (k = 0; k < 9; k++){
					if (Array[i][j][k] == 0){
						count2++;
						a = k + 1;
					}
				}
				if (count2 == 1){
					question[i][j] = a;
					Array[i][j][a -1] = 1;
					count1++;
				}
			}
		}
/*
		for(i = 0; i < 9; i++){
			for(j = 0; j < 9; j++){
				System.out.print(question[i][j]);
			}
			System.out.println();
		}
		System.out.println();
*/
		return count1;
	}



	public static int pattern2(int[][] question){
System.out.println("pattern2");
		int i, j, k, l, m, a, b, count2 = 0, count3 = 0, count4;
//		int Array[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
//		int[][][] Array2 = new int[9][9][9];

		for(i = 0; i < 9; i++){
			int yoko[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
			for(j = 0; j < 9; j++){

//横を見てそのマスに入らない数字に1をつける
				for (k = 0; k < 9; k++){
					if(question[i][k] != 0){
						Array[i][j][question[i][k] - 1] = 1;
					}
				}
				for (k = 0; k < 9; k++){	//横にすべて数字が入っていれば印をつける
					if(question[i][k] != 0){
						count2++;
						if(count2 == 9){
							yoko[i] = 1;
						}
					}
				}

//縦を見てそのマスに入らない数字に1をつける
				for (k = 0; k < 9; k++){
					if(question[k][j] != 0){
						Array[i][j][question[k][j] - 1] = 1;
					}
				}

//ブロックを見てそのマスに入らない数字に1をつける
				a = i % 3;
				b = j % 3;

				a = i - a;
				b = j - b;

				for (k = 0; k < 3; k++){
					for (l = 0; l < 3; l++){
						if (question[a + k][b + l] != 0){
							Array[i][j][question[a + k][b + l] - 1] = 1;
						}
					}
				}
			}
		}


//int counta = 0;
//for(i = 0; i < 9; i++){
//	for(j = 0; j < 9; j++){
//		for(k = 0; k < 9; k++){
//			System.out.print(Array[i][j][k]);
//			counta++;
//		}
//		System.out.println();
//	}
//}
//System.out.println(counta);

		for(i = 0; i < 9; i++){
			for(j = 0; j < 9; j++){
				if(question[i][j] != 0) continue;
				outside: for(k = 0; k < 9; k++){
					if(Array[i][j][k] != 0) continue;

//横の中でその数字が入る可能性があるマスがほかにあるか調べて、なければ入れる
					count4 = 0;
					for(l = 0; l < 9; l++){
						if(Array[i][l][k] != 0){
							count4++;
							if(count4 == 8){
								question[i][j] = k + 1;
								count3++;
								break outside;
							}
						}
					}

//縦の中でその数字が入る可能性があるマスがほかにあるか調べて、なければ入れる
					count4 = 0;
					for(l = 0; l < 9; l++){
						if(Array[l][j][k] != 0){
							count4++;
							if(count4 == 8){
								question[i][j] = k + 1;
								count3++;
								break outside;
							}
						}
					}

//ブロックの中でその数字が入る可能性があるマスがほかにあるか調べて、なければ入れる
					count4 = 0;
					a = i % 3;
					b = j % 3;

					a = i - a;
					b = j - b;

					for(l = 0; l < 3; l++){
						for(m = 0; m < 3; m++){
							if(Array[a + l][b + m][k] != 0){
								count4++;
								if(count4 == 8){
									question[i][j] = k + 1;
									count3++;
									break outside;
								}
							}
						}
					}
				}
			}
		}
/*
		for(i = 0; i < 9; i++){
			for(j = 0; j < 9; j++){
				System.out.print(question[i][j]);
			}
			System.out.println();
		}
		System.out.println();
*/
		return count3;
	}



//pattern3:予約の考え方
//count1を返す
	public static int pattern3(){
System.out.println("pattern3");

		int i, j, k, l, x, y, z, a, b, c, d, e, yoko = 0, tate = 0, block = 0,co0 = 0, count1 = 0, count2 = 0, count3 = 0, count4 = 0, yoko1 = 0, tate1 = 0, block1 = 0;
		int Array1[] = new int[9];
		int Array2[] = new int[9];
		int Array3[] = new int[9];

		for(i = 0; i < 9; i++){
			for(j = 0; j < 9; j++){
//1つのマスにフォーカス

				if(question[i][j] != 0) continue;

				co0 = 0;
				count2 = 0;	//そのマスに入る可能性のある数字の数
				yoko = 0;
				tate = 0;
				block = 0;

//横
//0の数が埋まっていない数字の数と同じならば縦へ
				if(j != 8){
					for(k = 0; k < 9; k++){
						if(question[i][k] == 0){
							yoko++;
						}
						Array1[k] = Array[i][j][k];
						if(Array[i][j][k] == 0){
							count2++;
						}
					}

					if(count2 != yoko){

						count3 = 0;
						count4 = 0;
						for(k = 0; k < 9; k++){
							Array3[k] = 0;
						}

						for(x = 1; (x + j) < 9; x++){
							if(question[i][x + j] != 0) continue;
							for(y = 0; y < 9; y++){
								Array2[y] = Array[i][x + j][y];
							}
							for(k = 0; k < 9; k++){
								if(Array1[k] == Array2[k]){
									count3++;
								}
							}
							if(count3 == 9){
								Array3[j] = 1;
								Array3[x + j] = 1;
								count4++;	//対象のマスとまったく同じ数字が入る可能性があるマスの数
								if(count2 == count4){
									for(k = 0; k < 9; k++){
										if(Array1[k] == 0){
											for(l = 0; l < 9; l++){
												if(Array3[l] != 1){
													Array[i][l][k] = 1;
													count1++;
												}
											}
										}
									}
								}
							}
						}
	System.out.print("横");
for(k = 0; k < 9; k++){
	System.out.print(Array3[k]);
}
	System.out.println();
					}
				}
//横終了

//縦開始
//0の数が埋まっていない数字の数と同じならばブロックへ
				if(i != 8){
					for(k = 0; k < 9; k++){
						if(question[k][j] == 0){
							tate++;
						}
						Array1[k] = Array[i][j][k];
						if(Array[i][j][k] == 0){
							count2++;
						}
					}

					if(count2 != tate){

//初期化
						count3 = 0;
						count4 = 0;
						for(k = 0; k < 9; k++){
							Array3[k] = 0;
						}

						for(x = 1; (x + i) < 9; x++){
							if(question[x + i][j] != 0) continue;
							for(y = 0; y < 9; y++){
								Array2[y] = Array[x + i][j][y];
							}
							for(k = 0; k < 9; k++){
								if(Array1[k] == Array2[k]){
									count3++;
								}
							}
							if(count3 == 9){
								Array3[i] = 1;
								Array3[x + i] = 1;
								count4++;	//対象のマスとまったく同じ数字が入る可能性があるマスの数
								if(count2 == count4){
									for(k = 0; k < 9; k++){
										if(Array1[k] == 0){
											for(l = 0; l < 9; l++){
												if(Array3[l] != 1){
													Array[l][j][k] = 1;
													count1++;
												}
											}
										}
									}
								}
							}
						}
	System.out.print("縦");
for(k = 0; k < 9; k++){
	System.out.print(Array3[k]);
}
	System.out.println();
					}
				}
//縦終了

			}
		}

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

