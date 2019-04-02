package test4;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main extends JFrame 
{
	public static Map<String, Integer> wordsStatistics;
	public static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private static JButton inputButton;//按钮
	private static JButton closeButton;
	private static JLabel input;//版面
	private static JFrame statistics;//框架
	private static JTextField file2;
	private static JTextField word2;//输入单词
	private static JTextField number2;//输入单词
	private static JLabel file ;//= new File("result.txt");
	private static JLabel word;
	private static JLabel number;

	public static FileReader is;
	static BufferedReader br;
    static String line;
	
	public Main ()
	{

		//初始化登陆界面
		Font font =new Font("黑体", Font.PLAIN, 20);//设置字体
	    statistics=new JFrame("统计指定单词词频");
		statistics.setSize(500, 400);
		input=new JLabel();
		
		file=new JLabel("输入文件名:");
		file.setBounds(20, 40, 150, 50);
		
		word=new JLabel("输入要统计的字符串:");
		word.setBounds(20, 80, 150, 50);
		
		number=new JLabel("输入高频词个数:");
		number.setBounds(20, 100, 150, 100);
		
		inputButton=new JButton("确定");         //更改成loginButton
		inputButton.setBounds(260, 250, 100, 50);
		inputButton.setFont(font);
		inputButton.setBorderPainted(false); 
		inputButton.setBorder(BorderFactory.createRaisedBevelBorder());
		//inputButton.setBackground(Color.green); 

		closeButton=new JButton("取消");
		closeButton.setBounds(370, 250, 100, 50);
		closeButton.setFont(font);
		closeButton.setBorderPainted(false); 
		closeButton.setBorder(BorderFactory.createLoweredBevelBorder());

		//加入文本框
		file2 = new JTextField();
		file2.setBounds(150, 40, 250, 40);
		
		word2=new JTextField();
		word2.setBounds(150, 80, 250, 40);
		
		number2=new JTextField();
		number2.setBounds(150, 120, 250, 40);
		
		input.add(file);
		input.add(file2);
		input.add(word2);		
		input.add(word);
		input.add(number);
		input.add(number2);
		input.add(inputButton);
		input.add(closeButton);
		
		statistics.add(input);
		statistics.setVisible(true);
		statistics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statistics.setLocation(300,400);
		
		statistics.getContentPane().setBackground(Color.WHITE); 

	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		//统计输入单词的频数
		Main ws =new Main();
	
		//点击查看结果
		ActionListener InputButton=new ActionListener() 
		{
	
			private int linenumber=0;
			int charnumber=0;
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				String admin=word2.getText();
				String num=number2.getText();
				String f=file2.getText();	
					
				long start=System.currentTimeMillis();   //获取开始时间
				
				if(f.isEmpty())
				{
					JOptionPane.showConfirmDialog(null, "请输入文件名！","提示",JOptionPane.DEFAULT_OPTION);
				}
				else 
				{
					try 
					{
						is = new FileReader(f);
					} 
					catch (FileNotFoundException e2) 
					{
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}		
			
					br = new BufferedReader(is);
											
					List<String> list = new ArrayList<String>();
					String read = null;
					try 
					{
						while((read = br.readLine()) != null)
						{  
							linenumber++;//行数
							charnumber+=read.length();//字符数
						   for (String w:read.split("[^a-zA-Z]")) 
						   {
						       if(w.length()!= 0)
						       {
						           list.add(w);  
						       }  
						   }  
						}
					} 
					catch (IOException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
					wordsStatistics= new TreeMap<String,Integer>();
					for (String l:list) 
					{
						if(wordsStatistics.get(l) != null)
						{  
							wordsStatistics.put(l,wordsStatistics.get(l) + 1);  
						}
						else
						{ 
							wordsStatistics.put(l,1); 
						} 
						
					}					
					SortResult rs = new SortResult();					
					wordsStatistics=rs.Sort(wordsStatistics,linenumber,charnumber);
					if(admin.isEmpty() && !num.isEmpty())
					{
						int n=Integer.parseInt(num);
						String print = "";
						ArrayList<Map.Entry<String,Integer>> resultlist = new ArrayList<Map.Entry<String,Integer>>(wordsStatistics.entrySet());
				        
				        Collections.sort(resultlist,new Comparator<Map.Entry<String,Integer>>()
				        {  
				            @Override  
				            public int compare(Entry<String, Integer> w1, Entry<String, Integer> w2) 
				            {  
				                return w2.getValue() - w1.getValue();  //降序  
				            }  
				        }); 
				        
				        for(int i = 0; i<n; i++)
				        {  
				        	print += resultlist.get(i).getKey()+ ": " +resultlist.get(i).getValue()+"    ";
				        }
				        
				        long end = System.currentTimeMillis();			     
		        		JOptionPane.showConfirmDialog(null, print+"\n运行时长"+(end-start)+"ms","结果",JOptionPane.DEFAULT_OPTION);
		        		
					}
					
					else if(!admin.isEmpty())
					{				
						Map<String,Integer> map = new TreeMap<String, Integer>();  
						String[] input= admin.split(" ");
					       
					    int i;
					    String print = "";
					    String print2 = "\n";
					    for(i=0; i<input.length; i++) 
					    {
					       	for(Entry<String, Integer> entry : wordsStatistics.entrySet()) 
					       	{ 
					       		if(input[i].equals(entry.getKey()))
					        	{
					        		map.put(entry.getKey(), entry.getValue());
	
				        			print2+=entry.getKey()+":\n";
					        		for(int j=0;j<entry.getValue()/100;j++)
					        			print2+="*";
				        			print2+="\n";
					        		print += entry.getKey() + ":" + entry.getValue()+"    "; 
					        		dataset.addValue(entry.getValue(),entry.getKey(),entry.getValue());
					        		break;
					        	}
					         } 
					     }
					    long end = System.currentTimeMillis();
					   	JOptionPane.showConfirmDialog(null, print+print2+"\n运行时长"+(end-start)+"ms","结果",JOptionPane.DEFAULT_OPTION);
					   	
					   	JFreeChart chart = ChartFactory.createBarChart3D(
	       		                "词频统计", // 图表标题
	                            "单词", // 文件夹轴的显示标签
	                            "数量", // 数值轴的显示标签
	                            dataset,//.dataset, // 数据集
	                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
	                            true,           // 是否显示图例(对于简单的柱状图必须是false)
	                            false,          // 是否生成工具
	                            false           // 是否生成URL链接
	                            );
	        
					   	paint p = new paint(chart);
		        		JFrame frame=new JFrame("Java数据统计图");
		        		frame.setLayout(new GridLayout(2,2,10,10));
		        		frame.add(p.getChartPanel());           //加入柱形图
		        		frame.setBounds(50, 50, 800, 600);
		        		frame.setVisible(true);
					}
					else
					{
					   	JOptionPane.showConfirmDialog(null, admin+"请输入要查询的信息！","提示",JOptionPane.DEFAULT_OPTION);					
					}
				}
			}
		};
		inputButton.addActionListener(InputButton);
		
		//取消事件的处理
		ActionListener CloseButton=new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub

				//ws.statistics.dispose();//销毁当前界面
				//Editor ed = new Editor();
				System.exit(0);//终止当前程序
			}
		};
       closeButton.addActionListener(CloseButton);		
    }
}
