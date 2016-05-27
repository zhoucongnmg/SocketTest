package Tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ����TCPЭ���Socketͨ�ţ�ʵ���û���½
 * ��������
 */
public class Server {
	public static void main(String[] args) {
		try {
			//1.����һ����������Socket����ServerSocket��ָ���󶨵Ķ˿ڣ��������˶˿�
			//backlog��������ָʾ�������ӵ����󣩵������г��ȱ�����Ϊ backlog ���������������ʱ�յ�����ָʾ����ܾ������ӡ�
			int backlog = 2;			
			ServerSocket serverSocket=new ServerSocket(8888,backlog);
            //serverSocket.bind(8888);
			Socket socket=null;
			//��¼�ͻ��˵�����
			int count=0;
			System.out.println("***�����������������ȴ��ͻ��˵�����***");
			
			//ѭ�������ȴ��ͻ��˵�����
			while(true){
				//����accept()������ʼ�������ȴ��ͻ��˵�����
				socket=serverSocket.accept();   //���������������ȡ������,���Դ�ʱ�������һֱΪ�գ�backlogʧЧ
				//����һ���µ��߳�
				ServerThread serverThread=new ServerThread(socket);
				//�����߳�
				serverThread.start();
				
				count++;//ͳ�ƿͻ��˵�����
				System.out.println("�ͻ��˵�������"+count);	
				InetAddress address=socket.getInetAddress();
				System.out.println("��ǰ�ͻ��˵�IP��"+address.getHostAddress());
				//break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
