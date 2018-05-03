//�}�X�ɐ��������������Array[]���X�V������brake

import java.lang.Math ;
import java.util.Random ;
import java.io.*;

public class Sudoku_ver_1_0_3 {

	static int question[][] = new int[9][9];



	public static void main(String[] args){

		int num;
		double time = 0;

		for(num = 0; num < 1000; num++){

		int i = 0;
		String filename = "question03.csv";
		int question[][] = readFile(filename);

		long start = System.currentTimeMillis();

		answer(question);

		long stop = System.currentTimeMillis();
//		System.out.print("\n" + (stop - start) + "\n");


		time += (stop - start);
		}
		System.out.print(time/1000);

	}



	public static void answer(int question[][]){
		int i = 1, i1 = 0, i2 = 0;

		while(i != 0){
			i1 = pattern1(question);
			i2 = pattern2(question);
			i = i1 + i2;
		}
	}



	public static int pattern1(int[][] question){
//System.out.println("pattern1");
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

				for (k = 0; k < 9; k++){	//���ɓ����Ă��鐔�����m�F���ă��X�g��1�ɂ���
					count2 = 0;
					if (question[i][k] != 0){
						Array[question[i][k] - 1] = 1;
					}
					if (question[i][k] != 0){	//���ɂ��ׂĂ̐����������Ă���ꍇ�Ƀt���O�𗧂Ă�
						count2++;
					}
					if (count2 == 9) {
						yoko[i] = 1;
					}
				}

				for (k = 0; k < 9; k++){	//�c�ɓ����Ă��鐔�����m�F���ă��X�g��1�ɂ���
					if (question[k][j] != 0){
						Array[question[k][j] - 1] = 1;
					}
				}

//�u���b�N�̒��ł̈ʒu���m�F���āA���ォ�珇�ɐ������m�F���ă��X�g��1�ɂ���
				int a = i % 3;
				int b = j % 3;

				a = i - a;
				b = j - b;

				for (k = 0; k < 3; k++){
					for (m = 0; m < 3; m++){
//System.out.println(a + " , " + k);
//System.out.println(b + " , " + m);
						if (question[a + k][b + m] != 0){
							Array[question[a + k][b + m] - 1] = 1;
						}
					}
				}
//for(int c = 0; c < 9; c++){
//	System.out.print(Array[c]);
//}
//System.out.println();
//���X�g���m�F����1�ɂȂ��Ă��Ȃ�������1�����ł���΂��̐������}�X�ɓ����
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
//System.out.println("pattern2");
		int i, j, k, l, m, a, b, count2 = 0, count3 = 0, count4;
		int Array[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[][][] Array2 = new int[9][9][9];

		for(i = 0; i < 9; i++){
			int yoko[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
			for(j = 0; j < 9; j++){
				if(question[i][j] != 0){
					for(k = 0; k < 9; k++){
						Array2[i][j][k] = 1;
					}
					continue;
				}

//�������Ă��̃}�X�ɓ���Ȃ�������1������
				for (k = 0; k < 9; k++){
					if(question[i][k] != 0){
						Array2[i][j][question[i][k] - 1] = 1;
					}
				}
				for (k = 0; k < 9; k++){	//���ɂ��ׂĐ����������Ă���Έ������
					if(question[i][k] != 0){
						count2++;
						if(count2 == 9){
							yoko[i] = 1;
						}
					}
				}

//�c�����Ă��̃}�X�ɓ���Ȃ�������1������
				for (k = 0; k < 9; k++){
					if(question[k][j] != 0){
						Array2[i][j][question[k][j] - 1] = 1;
					}
				}

//�u���b�N�����Ă��̃}�X�ɓ���Ȃ�������1������
				a = i % 3;
				b = j % 3;

				a = i - a;
				b = j - b;

				for (k = 0; k < 3; k++){
					for (l = 0; l < 3; l++){
						if (question[a + k][b + l] != 0){
							Array2[i][j][question[a + k][b + l] - 1] = 1;
						}
					}
				}
			}
		}


//int counta = 0;
//for(i = 0; i < 9; i++){
//	for(j = 0; j < 9; j++){
//		for(k = 0; k < 9; k++){
//			System.out.print(Array2[i][j][k]);
//			counta++;
//		}
//		System.out.println();
//	}
//}
//System.out.println(counta);

		for(i = 0; i < 9; i++){
			for(j = 0; j < 9; j++){
				if(question[i][j] != 0) continue;
				for(k = 0; k < 9; k++){
					Array[k] = Array2[i][j][k];
				}
				outside: for(k = 0; k < 9; k++){
					if(Array[k] != 0) continue;

//���̒��ł��̐���������\��������}�X���ق��ɂ��邩���ׂāA�Ȃ���Γ����
					count4 = 0;
					for(l = 0; l < 9; l++){
						if(Array2[i][l][k] != 0){
							count4++;
							if(count4 == 8){
								question[i][j] = k + 1;
								count3++;
								break outside;
							}
						}
					}

//�c�̒��ł��̐���������\��������}�X���ق��ɂ��邩���ׂāA�Ȃ���Γ����
					count4 = 0;
					for(l = 0; l < 9; l++){
						if(Array2[l][j][k] != 0){
							count4++;
							if(count4 == 8){
								question[i][j] = k + 1;
								count3++;
								break outside;
							}
						}
					}

//�u���b�N�̒��ł��̐���������\��������}�X���ق��ɂ��邩���ׂāA�Ȃ���Γ����
					count4 = 0;
					a = i % 3;
					b = j % 3;

					a = i - a;
					b = j - b;

					for(l = 0; l < 3; l++){
						for(m = 0; m < 3; m++){
							if(Array2[a + l][b + m][k] != 0){
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


	public static int[][] readFile(String filename){

		int i, j = 0, k;
		String[] questionStr = new String[9];
		int[][] question;
		question = new int[9][9];

		try {
			FileReader fr = new FileReader("C:\\Users\\roman\\Desktop\\question\\" + filename);
			BufferedReader br = new BufferedReader(fr);

//�ǂݍ��񂾃t�@�C�����P�s����������
			String line;
			while ((line = br.readLine()) != null) {
//��؂蕶��","�ŕ�������
				questionStr = line.split(",", -1);
				for (i = 0; i < 9; i++){
					k = Integer.parseInt(questionStr[i]);
					question[j][i] = k;
				}
				j++;
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
//�I������
			br.close();

		} catch (IOException ex) {
//��O����������
			ex.printStackTrace();
		}
		return question;
	}
}

