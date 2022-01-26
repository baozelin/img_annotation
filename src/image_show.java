import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class image_show extends JFrame {

    //String base_path = "/Users/zelinbao/Documents/folder3.txt/video_data/";
    //String score_file = "/Users/zelinbao/Downloads/zelin_label_v3.txt";

    String base_path = "/Users/zelinbao/Downloads/0121_easy_1000/";
    String score_file = "/Users/zelinbao/Downloads/prediction_with_chanpin_eval.txt";

    JPanel panel1 = new JPanel();
    JPanel contentPane = new JPanel();
    JButton open=new JButton("select file");


    JButton notebutton=new JButton("note label");
    JTextArea jtxtarea = new JTextArea();
    JLabel scoreLabel = new JLabel();


    JButton nextButton = new JButton("next");
    ArrayList<String> direct_array = new ArrayList<>();
    int index = 0;

    HashMap<String,String> map = new HashMap<>();
    HashSet<String> set = new HashSet<>();



    public image_show() throws IOException {
        scoreLabel.setText("recommand score");
        readFile();

        java.io.File file3 = new java.io.File(base_path);
        String[] direct_path = file3.list();

        System.out.println(direct_path.length);

        for(int i = 0; i < direct_path.length; i++){
            if(direct_path[i].indexOf(".") == -1){
                direct_array.add(base_path +"/" + direct_path[i]);
            }

        }


        String[] direction_path = new String[1];

        JPanel panel2 = new JPanel();
        add(panel2, BorderLayout.NORTH);
        //add(notebutton, BorderLayout.NORTH);
        //add(jtxtarea, BorderLayout.NORTH);
        jtxtarea.setPreferredSize(new Dimension(100, 20));
        panel2.add(open,BorderLayout.NORTH);
        panel2.add(notebutton, BorderLayout.CENTER);
        panel2.add(jtxtarea, BorderLayout.SOUTH);
        panel2.add(nextButton,BorderLayout.EAST);
        panel2.add(scoreLabel,BorderLayout.NORTH);

        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (index < direct_array.size()) {



                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setBounds(100, 100, 1000, 1000); //位置（100 ，100）宽：450，高300

                    //Border描述了面板四周的边界（属于面板内部），EmptyBorder为5的一个空白的边界；
                    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                    contentPane.setLayout(new BorderLayout(0, 0)); //边界布局

                    contentPane.removeAll();


                    //添加新的JPanel到JScrollPane下
                    JPanel panel = new JPanel();




                    java.io.File file2 = new java.io.File(direct_array.get(index));
                    String[] list = file2.list();
                    System.out.println(file2.getAbsolutePath());

                    direction_path[0] = file2.getAbsolutePath();
                    //System.out.println(x);

                    int x = list.length; //列：5
                    int y = 1; //行：5

                    System.out.println(x);
                    panel.setLayout(new GridLayout(x, y));

                    for (int i = 0; i < x; i++) {

                        //设置宽100，高100的JButton
                        //JButton button = new JButton(String.valueOf(i));
                        // button.setPreferredSize(new Dimension(100, 100));
                        JLabel label = showImg(file2.getAbsolutePath() + '/' + list[i]);
                        //System.out.println(file2.getAbsolutePath() + '/' + list[i]);
                        panel.add(label);
                        //添加按钮动作监听
                    }

                    JScrollPane scrollPane = new JScrollPane(panel);
                    //不能使用
                    //scrollPane.add(panel);
                    contentPane.add(scrollPane, BorderLayout.CENTER);

                    revalidate();
                    repaint();
                    add(contentPane, BorderLayout.CENTER);


                    //get mid
                    String[] mids = file2.getAbsolutePath().split("/");


                    scoreLabel.setText(map.get(mids[mids.length-1]));


                    if(set.contains(mids[mids.length-1])){

                        scoreLabel.setText("-1");
                    }

                }
                index++;
            }
        });
        notebutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                writeTxt(base_path +'/' + "note.txt", direction_path[0] + "\t" + jtxtarea.getText() +'\n');
            }

        });


        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc=new JFileChooser(base_path);
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                jfc.showDialog(new JLabel(), "选择");
                File file=jfc.getSelectedFile();
                if(file.isDirectory()){
                    System.out.println("文件夹:"+file.getAbsolutePath());
                    //direction_path = file.getAbsolutePath();

                }else if(file.isFile()){
                    System.out.println("文件:"+file.getAbsolutePath());
                }
                System.out.println(jfc.getSelectedFile().getName());
                System.out.println(jfc.getSelectedFile().getAbsolutePath());
                direction_path[0] = jfc.getSelectedFile().getAbsolutePath();


                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(300, 300, 1000, 1000); //位置（100 ，100）宽：450，高300

                contentPane.removeAll();

                //Border描述了面板四周的边界（属于面板内部），EmptyBorder为5的一个空白的边界；
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPane.setLayout(new BorderLayout(0, 0)); //边界布局
                add(contentPane, BorderLayout.CENTER);



                //添加新的JPanel到JScrollPane下
                JPanel panel = new JPanel();


                if(direction_path[0] != null){
                    java.io.File file2 = new java.io.File(direction_path[0]);
                    String[] list = file2.list();
                    System.out.println(file2.getAbsolutePath());

                    int x = list.length; //列：5
                    int y = 1; //行：5

                    System.out.println(x);
                    panel.setLayout(new GridLayout(x, y));
                    for (int i = 0; i < x; i++) {
                        //设置宽100，高100的JButton
                        //JButton button = new JButton(String.valueOf(i));
                        // button.setPreferredSize(new Dimension(100, 100));
                        JLabel label = showImg(file.getAbsolutePath()+'/'+list[i]);
                        panel.add(label);
                        //添加按钮动作监听

                    }


                }


                //添加Jscrollpane滚动,只能用
                JScrollPane scrollPane = new JScrollPane(panel);
                //不能使用
                //scrollPane.add(panel);
                contentPane.add(scrollPane, BorderLayout.CENTER);
                //splitpane.setLeftComponent(contentPane);



                repaint();


            }
        });

    }
    public static void writeTxt(String txtPath,String content){
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        try {
            if(!file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void readFile() throws IOException {
        FileReader fr=new FileReader(score_file);
        BufferedReader br=new BufferedReader(fr);
        String line="";
        String[] arrs=null;
        while ((line=br.readLine())!=null) {
            arrs=line.split("\\s+");
            map.put(arrs[0],arrs[1]);
            System.out.println(arrs[0]+arrs[1]);


            //extra set dealing
            if(arrs.length > 3){
                set.add(arrs[0]);
            }
            // needed to del
        }
        br.close();
        fr.close();
    }




    private JLabel showImg(String image_path){
        ImageIcon icon = new ImageIcon("/Users/zelinbao/Documents/to_Zelin/4504640739604841/1.jpg");
        //JLabel j = new JLabel(icon);
        icon = new ImageIcon(new ImageIcon(image_path).getImage());

        JLabel jlblImageViewer = new JLabel();
        jlblImageViewer.setIcon(icon);
        return jlblImageViewer;

    }


    public static void main(String[] args) throws IOException {

        image_show show_frame = new image_show();
        show_frame.setSize(500, 500);
        show_frame.setVisible(true);


    }
}



