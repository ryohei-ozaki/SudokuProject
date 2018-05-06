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

for(i = 0; i < 9; i++){
	for(j = 0; j < 9; j++){
		System.out.print(question[i][j]);
	}
	System.out.println();
}
System.out.println();

//�I������
			br.close();

		} catch (IOException ex) {
//��O����������
			ex.printStackTrace();
		}
		return question;
	}
}

