/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojekt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import oru.inf.InfDB;
import oru.inf.InfException;



/**
 *
 * @author isakj
 */
public class BlogV2 extends javax.swing.JFrame {
    
    InfDB idb;
    Profile profile;
    Validator validator;
    SimpleDateFormat dateFormat;
    NewCategoryWindow newCategory;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    ArrayList<BlogPost> blogPosts;
    
    String userID;
    int displayPage;
    

    MouseAdapter clickListenerNext = (new MouseAdapter() {  
        public void mouseClicked(MouseEvent c)  
        {   
           displayPage++;
           displayPosts(displayPage);
        }  
    });
    
        MouseAdapter clickListenerPrevious = (new MouseAdapter() {  
        public void mouseClicked(MouseEvent c)  
        {  
            displayPage--;
            displayPosts(displayPage);
        }  
    });
        
    MouseAdapter hoverListener = (new MouseAdapter() {
         public void mouseEntered(MouseEvent en) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
         }
         public void mouseExited(MouseEvent ex) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }
    });
    
    public BlogV2(String userID) {
        initComponents();
        this.userID = userID;
        idb = TestProjekt.getDB();
        validator = new Validator();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        newCategory = new NewCategoryWindow();
        setCategoryCbs(); 
        
        setLocationRelativeTo(this);
        pnlNewPostTab.setVisible(false);
        txtText.getDocument().addDocumentListener(new DocumentListener() {
            
            @Override
            public void removeUpdate(DocumentEvent remove) {
                updateLetterCount();
            }

            @Override
            public void insertUpdate(DocumentEvent insert) {
                updateLetterCount();
            }

            @Override
            public void changedUpdate(DocumentEvent changed) {
                updateLetterCount();
            }
        });
        
        lblNext.addMouseListener(clickListenerNext);///////////////////////////////////////////////
        lblNext.addMouseListener(hoverListener);//////////////////////////////////////////////
        lblPrevious.addMouseListener(clickListenerPrevious);
        lblPrevious.addMouseListener(hoverListener);
        
        blogPosts = new ArrayList();
        setPosts(getFavouriteCategories(userID));
        displayPage = 0;

        displayPosts(displayPage);
        if (isAdmin()) {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } else {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
    
    
    public void addPost(BlogPost post) {
        blogPosts.add(0, post);
    }
    
    public boolean isAdmin() {
        String admin = "";
        try {
        String query = "select Adminjanej from anvandare where anvandar_id = " + userID;
        admin = idb.fetchSingle(query);
        } catch(InfException ie) {
            System.out.println(ie.getMessage());
        }
        return admin.equals("J");
    }
    
    public void displayPost(int templateNumber, int page) {
        BlogPostTemplate[] templates = {post1, post2, post3, post4, post5};
        templates[templateNumber].notEditable();
        BlogPostTemplate template = templates[templateNumber];
        //int post = page == 0 ? templateNumber : page + 4 + templateNumber;
        int post = page * 5 + templateNumber;
        if (templateNumber % 2 == 1) {
            template.getTextArea().setBackground(new java.awt.Color(246, 246, 246));
            template.getTitleField().setBackground(new java.awt.Color(246, 246, 246));
        }
        if (post < blogPosts.size()) {
            template.setVisible(true);
            template.setAuthor(blogPosts.get(post).getAuthor());
            template.setText(blogPosts.get(post).getText());
            template.setTitle(blogPosts.get(post).getTitle());
            template.setId(blogPosts.get(post).getID());
            template.setCategory("#"+findCategoryName(blogPosts.get(post).getCategory()));
            
            template.setUserID(userID);
            
            String date = blogPosts.get(post).getDate();
            template.setDate(date);
            
            if(findUserName(userID).equals(blogPosts.get(post).getAuthor())) {
                templates[templateNumber].getEditButton().setVisible(true);
            } else {
                templates[templateNumber].getEditButton().setVisible(false);
            }
       }
        else {
            template.setVisible(false);
        }
    }

    public void displayPosts(int page) {
        for (int i = 0; i < 5; i++) {
            displayPost(i, page);
        }
        displayPageInfo();
    }
    
    public void displayPageInfo() {
        int showing = 0;
        int size = blogPosts.size();
        if(size <= 4) {
            showing = size;
        } else if (displayPage == 0) {
            showing = 5;
        } else {
            System.out.println(size);
            showing = size - (displayPage * 5) > 5 ? 5 : size - ((displayPage) * 5);
        }
        
        if(displayPage == 0) {
            lblPrevious.setVisible(false);
        } else {
            lblPrevious.setVisible(true);    
        }
//        if ((displayPage + 1) * 5 >= size) {
//            lblNext.setVisible(false);
//        }   
        
        lblShowingAmount.setText(Integer.toString(showing));
        lblTotalAmount.setText(size + " inl�gg");
        
    }
    
    
    
    ////////////// New Post Tab /////////////////////
    
    private int countLetters(String text) {
        text = text.replaceAll(" ", "");
        return text.length();
    }
    
    private void updateLetterCount() {
        String text = txtText.getText();
        int count = text.length();
        String LetterCount = Integer.toString(count);
        lblLetterCount.setText(LetterCount);
        if(count <= 2500) {
            updateLetterCountColor(false);
        } else {
            updateLetterCountColor(true);
        }
    }
    
    private void updateLetterCountColor (boolean tooManyLetters) {
        if(tooManyLetters) {
            lblMaxLetters.setForeground(Color.red);
            lblLetterCount.setForeground(Color.red);
        } else {
            lblMaxLetters.setForeground(Color.black);
            lblLetterCount.setForeground(Color.black);
        }
        updatePostButton(tooManyLetters);
    }
    
    private void updatePostButton(boolean tooManyLetters) {
        if(tooManyLetters) {
            btnPost.setEnabled(false);
        } else {
            btnPost.setEnabled(true);
        }
    }
    
    public String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    
    public void setPosts(ArrayList<String> categories) {
        blogPosts.clear();
        String valdKategori = cbCategory.getSelectedItem().toString();
        int valdKategoriInt = 0;
        if (!valdKategori.equals("-- Kategori --")) {
            valdKategoriInt = Integer.parseInt(findCategoryID(valdKategori));
        }
        try {
            try {
                
                String cbValue = cbShowFormal.getSelectedItem().toString();
                String table; // = (cbValue.equals("Formella inl�gg")) ? "Formell" : "Informell";
                if (cbValue.equals("Formella inl�gg")) {
                    table = "Formell";
                } else {
                    table = "Informell";
                }
                String searchString = txtSearch.getText();
                //String where = ", " + table + " WHERE Inlagg.InlaggsID = " + table + ".InlaggsID" +
                //             " AND Text like '%" + searchString + "%'";
                //String query = "Select * FROM Inlagg" + where;
                //System.out.println(query);
                String query = "Select * from inlagg where text like '%" + searchString + "%' AND ";
                if(valdKategori.equals("-- Kategori --")){
               
                if(categories.size() > 0){
                    query += " kaid =";
                    for (int i = 0; i < categories.size(); i++) {
                        if (i == 0) {
                            query += categories.get(0);
                        } else {
                            query += " or KAID = " + categories.get(i);
                        }
                    }
                    query += " and";
                }
                }
                query += " inlaggsid in (select inlaggsid from " + table + ")";
                query += " order by inlaggsid";
                System.out.println("Query1: " + query);
                ArrayList<HashMap<String, String>> posts = new ArrayList();
                if (categories.size() > 0) {
                    posts = idb.fetchRows(query);
                }
                
                int amount = Integer.parseInt(idb.fetchSingle("Select count(*) from inlagg "));
                String query2 = "Select * from inlagg where text like '%" + searchString + "%' AND KAID != ";
                if (categories.size() > 0 && valdKategori.equals("-- Kategori --")) {

                    for (int i = 0; i < categories.size(); i++) {
                        if (i == 0) {
                            query2 += categories.get(0);
                        } else {
                            query2 += " and not KAID = " + categories.get(i);
                        }
                    }
                    query2 += " and inlaggsid in (select inlaggsid from " + table + ")";
                    query2 += " order by inlaggsid";
                } else {
                    query2 = "Select * from inlagg where text like '%" + searchString + "%' AND inlaggsid in (select inlaggsid from " + table + ") ";
                    if (!valdKategori.equals("-- Kategori --")) {
                        query2 += " and KAID = " + valdKategoriInt;
                    }
                }
                System.out.println("Query 2: " + query2);
                if (amount > posts.size()) {
                    ArrayList<HashMap<String, String>> posts2 = idb.fetchRows(query2);
                    if (!posts2.isEmpty()) {
                        for (HashMap<String, String> post : posts2) {
                            String rubrik = post.get("RUBRIK");
                            String text = post.get("TEXT");
                            String inlaggsid = post.get("INLAGGSID");
                            String kategori = post.get("KAID");
                            String id = post.get("ANVANDAR_ID");
                            String datum = post.get("DATUM");
                            BlogPost blogPost = new BlogPost(inlaggsid, findUserName(id), datum, rubrik, text, kategori);
                            addPost(blogPost);
                        }
                    }
                }
                if (valdKategori.equals("-- Kategori --")) {
                    if (!posts.isEmpty()) {
                        for (HashMap<String, String> post : posts) {
                            String rubrik = post.get("RUBRIK");
                            String text = post.get("TEXT");
                            String inlaggsid = post.get("INLAGGSID");
                            String kategori = post.get("KAID");
                            String id = post.get("ANVANDAR_ID");
                            String datum = post.get("DATUM");
                            BlogPost blogPost = new BlogPost(inlaggsid, findUserName(id), datum, rubrik, text, kategori);
                            addPost(blogPost);
                        }
                    }
                }
            } catch (NullPointerException npe) {

            }
        } catch (InfException ie) {
            System.out.println(ie.getMessage());
        }
    }
    
    public void filterPosts() {
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBlogTab = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        scrollPanel = new javax.swing.JScrollPane();
        pnlBlogPosts = new javax.swing.JPanel();
        post1 = new testprojekt.BlogPostTemplate();
        post2 = new testprojekt.BlogPostTemplate();
        post3 = new testprojekt.BlogPostTemplate();
        post4 = new testprojekt.BlogPostTemplate();
        post5 = new testprojekt.BlogPostTemplate();
        lblNext = new javax.swing.JLabel();
        lblPrevious = new javax.swing.JLabel();
        lblShowing = new javax.swing.JLabel();
        lblShowingAmount = new javax.swing.JLabel();
        lblSlash = new javax.swing.JLabel();
        lblTotalAmount = new javax.swing.JLabel();
        btnNewPost = new javax.swing.JButton();
        lblFilter = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox<>();
        btnShow = new javax.swing.JToggleButton();
        cbShowFormal = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSokProfil = new javax.swing.JButton();
        txtSok = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        pnlNewPostTab = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        scrollPane = new javax.swing.JScrollPane();
        txtText = new javax.swing.JTextArea();
        cbCategoryNewPost = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btnPost = new javax.swing.JButton();
        lblLetterCount = new javax.swing.JLabel();
        lblMaxLetters = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        cbFormal = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        scrollPanel.setForeground(new java.awt.Color(255, 255, 255));
        scrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlBlogPosts.setEnabled(false);

        post2.setBackground(new java.awt.Color(246, 246, 246));

        post4.setBackground(new java.awt.Color(246, 246, 246));

        lblNext.setText("<HTML><U>N�sta&gt;&gt;</U></HTML>");

        lblPrevious.setText("<HTML><U>&lt;&lt;F�rg�ende</U></HTML>");
        lblPrevious.setToolTipText("");

        lblShowing.setText("Visar  ");

        lblShowingAmount.setText("0");

        lblSlash.setText("/");

        lblTotalAmount.setText("5 inl�gg");

        javax.swing.GroupLayout pnlBlogPostsLayout = new javax.swing.GroupLayout(pnlBlogPosts);
        pnlBlogPosts.setLayout(pnlBlogPostsLayout);
        pnlBlogPostsLayout.setHorizontalGroup(
            pnlBlogPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(post2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(post3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(post4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(post5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(post1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlBlogPostsLayout.createSequentialGroup()
                .addGroup(pnlBlogPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBlogPostsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblShowing)
                        .addGap(0, 0, 0)
                        .addComponent(lblShowingAmount))
                    .addGroup(pnlBlogPostsLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlBlogPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBlogPostsLayout.createSequentialGroup()
                        .addComponent(lblSlash)
                        .addGap(0, 0, 0)
                        .addComponent(lblTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBlogPostsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );
        pnlBlogPostsLayout.setVerticalGroup(
            pnlBlogPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBlogPostsLayout.createSequentialGroup()
                .addComponent(post1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(post2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(post3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(post4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(post5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(pnlBlogPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShowing)
                    .addComponent(lblShowingAmount)
                    .addComponent(lblTotalAmount)
                    .addComponent(lblSlash))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBlogPostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPanel.setViewportView(pnlBlogPosts);

        btnNewPost.setText("Skapa inl�gg");
        btnNewPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPostActionPerformed(evt);
            }
        });

        lblFilter.setText("Filter");

        cbCategory.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbCategoryPopupMenuWillBecomeVisible(evt);
            }
        });
        cbCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbCategoryMouseClicked(evt);
            }
        });
        cbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoryActionPerformed(evt);
            }
        });

        btnShow.setText("Visa");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        cbShowFormal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Formella inl�gg", "Informella inl�gg" }));

        jLabel1.setText("Fritext");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSokProfil.setText("S�k");
        btnSokProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSokProfilActionPerformed(evt);
            }
        });

        jButton4.setText("Min profil");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("M�ten");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setText("S�k profil");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbShowFormal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                .addComponent(btnNewPost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblFilter)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSok, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSokProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 65, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbShowFormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNewPost, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSokProfil))
                .addGap(115, 115, 115))
            .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlBlogTabLayout = new javax.swing.GroupLayout(pnlBlogTab);
        pnlBlogTab.setLayout(pnlBlogTabLayout);
        pnlBlogTabLayout.setHorizontalGroup(
            pnlBlogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlBlogTabLayout.setVerticalGroup(
            pnlBlogTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlNewPostTab.setPreferredSize(new java.awt.Dimension(853, 447));

        lblTitle.setText("Titel");

        txtTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTitleActionPerformed(evt);
            }
        });

        txtText.setColumns(20);
        txtText.setLineWrap(true);
        txtText.setRows(5);
        txtText.setWrapStyleWord(true);
        txtText.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTextPropertyChange(evt);
            }
        });
        scrollPane.setViewportView(txtText);

        cbCategoryNewPost.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                cbCategoryNewPostPopupMenuWillBecomeVisible(evt);
            }
        });
        cbCategoryNewPost.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbCategoryNewPostMouseClicked(evt);
            }
        });
        cbCategoryNewPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoryNewPostActionPerformed(evt);
            }
        });

        jButton1.setText("Ny kategori");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnPost.setText("Posta");
        btnPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostActionPerformed(evt);
            }
        });

        lblLetterCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLetterCount.setText("0");

        lblMaxLetters.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaxLetters.setText("/2500");

        jButton2.setText("Tillbaka");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cbFormal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Formellt inl�gg", "Informellt inl�gg" }));

        javax.swing.GroupLayout pnlNewPostTabLayout = new javax.swing.GroupLayout(pnlNewPostTab);
        pnlNewPostTab.setLayout(pnlNewPostTabLayout);
        pnlNewPostTabLayout.setHorizontalGroup(
            pnlNewPostTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewPostTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewPostTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNewPostTabLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(291, 291, 291)
                        .addComponent(btnPost, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 440, Short.MAX_VALUE))
                    .addComponent(scrollPane)
                    .addGroup(pnlNewPostTabLayout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitle))
                    .addGroup(pnlNewPostTabLayout.createSequentialGroup()
                        .addComponent(cbFormal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cbCategoryNewPost, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLetterCount, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblMaxLetters)))
                .addContainerGap())
        );
        pnlNewPostTabLayout.setVerticalGroup(
            pnlNewPostTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewPostTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewPostTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlNewPostTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxLetters)
                    .addComponent(lblLetterCount)
                    .addComponent(cbCategoryNewPost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(cbFormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(pnlNewPostTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPost, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNewPostTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBlogTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNewPostTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBlogTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPostActionPerformed
        // TODO add your handling code here:
        pnlNewPostTab.setVisible(true);
        pnlBlogTab.setVisible(false);
    }//GEN-LAST:event_btnNewPostActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:f
        setPosts(getFavouriteCategories(userID));
        displayPosts(0);
    }//GEN-LAST:event_btnShowActionPerformed

    private void txtTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTitleActionPerformed

    private void txtTextPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTextPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTextPropertyChange

    private void cbCategoryNewPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoryNewPostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoryNewPostActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        newCategory.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostActionPerformed
        // TODO add your handling code here:
        try {
            String author = idb.fetchSingle("select Anvandar_namn from anvandare where anvandar_id = " + userID);
            String title = txtTitle.getText();
            String text = txtText.getText();
            String category = cbCategoryNewPost.getSelectedItem().toString();

            if (!category.equals("-- Kategori --")) {
                category = findCategoryID(category);
            }

            if (validator.validateNewPost(title, text, category)) {

                int id;
                if (idb.fetchSingle("SELECT count (*) FROM Inlagg").equals("0")) {
                    id = 1;
                } else {
                    String maxInlaggsID = idb.fetchSingle("SELECT max (inlaggsid) FROM Inlagg");

                    int maxID = Integer.parseInt(maxInlaggsID);
                    id = maxID + 1;
                }

                String postID = Integer.toString(id);

                String date = sdf.format(new Date());
                String query = "INSERT into Inlagg values('" + title + "', '" + text + "', " + id + ", " + userID + ", " + category + ", '" + date + "');";
                idb.insert(query);
                if (cbFormal.getSelectedItem().equals("Formellt inl�gg")) {
                    query = "INSERT INTO Formell values(" + id + ")";
                    idb.insert(query);
                } else {
                    query = "INSERT INTO Informell values(" + id + ")";
                    idb.insert(query);
                }

                BlogPost post = new BlogPost(postID, author, date, title, text, category);
                addPost(post);
                displayPage = 0;
                displayPosts(displayPage);
                pnlNewPostTab.setVisible(false);
                pnlBlogTab.setVisible(true);
            }
        } catch (InfException ie) {
            System.out.println(ie.getMessage() + "test");
        }
    }//GEN-LAST:event_btnPostActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        pnlBlogTab.setVisible(true);
        pnlNewPostTab.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbCategoryNewPostMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoryNewPostMouseClicked
       // setCategoryCbs();
    }//GEN-LAST:event_cbCategoryNewPostMouseClicked

    private void cbCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoryMouseClicked
       // setCategoryCbs();
    }//GEN-LAST:event_cbCategoryMouseClicked

    private void cbCategoryPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCategoryPopupMenuWillBecomeVisible
        try {
            if(idb.fetchSingle("SELECT count (*) FROM KATEGORI").equals("0")){
                JOptionPane.showMessageDialog(null, "Det finns ingen kategori tillagd");
            }
            setCategoryCbs();
        } catch (InfException ex) {
            Logger.getLogger(BlogV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbCategoryPopupMenuWillBecomeVisible

    private void cbCategoryNewPostPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCategoryNewPostPopupMenuWillBecomeVisible
        try {
            if(idb.fetchSingle("SELECT count (*) FROM KATEGORI").equals("0")){
                JOptionPane.showMessageDialog(null, "Det finns ingen kategori tillagd");
            }
            setCategoryCbs();
        } catch (InfException ex) {
            Logger.getLogger(BlogV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbCategoryNewPostPopupMenuWillBecomeVisible

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoryActionPerformed

    private void btnSokProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokProfilActionPerformed
        // TODO add your handling code here:
      
        try {
            String anvandaresok = txtSok.getText();
            String an = this.idb.fetchSingle("select ANVANDAR_ID from ANVANDARE where ANVANDAR_NAMN = '" + anvandaresok + "'");
            System.out.println(an);
            String na = this.idb.fetchSingle("select NAMN from ANVANDARE where ANVANDAR_ID = '" + an + "'");
            System.out.println(na);
            String em = this.idb.fetchSingle("select EPOST from ANVANDARE where ANVANDAR_ID= '" + an + "'");
            System.out.println(em);
            String ph = this.idb.fetchSingle("select TELE from ANVANDARE where ANVANDAR_ID = '" + an + "'");
            System.out.println(ph);

            if (anvandaresok.equals(idb.fetchSingle("select anvandar_namn from anvandare where anvandar_id = " + an))) {
                //this.setVisible(false);
                new Profile(idb, na, ph, em).setVisible(true);

            } else {

                JOptionPane.showMessageDialog(null, "Kunde inte hitta anvandaren");
            }
        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "Kunde inte hitta anv�ndaren");
            System.out.println("Internt felmeddelande:" + e.getMessage());
        }
    }//GEN-LAST:event_btnSokProfilActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new PersonligProfil(userID).setVisible(true);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new Kalendern(userID).setVisible(true); 
    }//GEN-LAST:event_jButton5ActionPerformed

    public String findCategoryID(String category) {    
        String result = "";
        try {
            String query = "SELECT KAID FROM Kategori WHERE Namn = '" + category + "'";
            result = idb.fetchSingle(query);
        } catch(InfException ie) {
                
        }
        return result;
    }
    
    public String findCategoryName(String id) {
        String query = "Select Namn from Kategori where KAID = " + id;
        String result = "";
        try {
            result = idb.fetchSingle(query);
        } catch(InfException ie) {
            System.out.println(ie.getMessage());
        }
        return result;
    }
    
    public String findUserName(String id) {   
        String result = "";
        try {
            String query = "SELECT Anvandar_namn FROM Anvandare WHERE Anvandar_ID = '" + id + "'";
            result = idb.fetchSingle(query);
        } catch(InfException ie) {
                
        }
        return result;
    }
   
   public void setCategoryCbs(){ 
        try {
            
            if(idb.fetchSingle("SELECT count (*) FROM KATEGORI").equals("0")){
            
            }
            else{
            ArrayList<String> kategorier = idb.fetchColumn("SELECT namn FROM KATEGORI");
            DefaultComboBoxModel kategori = new DefaultComboBoxModel();
            kategori.addElement("-- Kategori --");
            for(String listaKategorier : kategorier){
                kategori.addElement(listaKategorier);
            
           }
            cbCategory.setModel(kategori);
            cbCategoryNewPost.setModel(kategori);
            }
        } catch (InfException ex) {
            Logger.getLogger(BlogV2.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public ArrayList<String> getFavouriteCategories(String userID){
            ArrayList<String> resultat = new ArrayList();
       try{
           if(!idb.fetchSingle("SELECT count (*) FROM ANVANDARE_KATEGORI where anvandare = " + userID).equals("0")){
            String query = "Select kategori from anvandare_kategori where anvandare = " + userID;
            resultat = idb.fetchColumn(query);
            }
       } catch(InfException ie){
           
       }
       return resultat;
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewPost;
    private javax.swing.JButton btnPost;
    private javax.swing.JToggleButton btnShow;
    private javax.swing.JButton btnSokProfil;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JComboBox<String> cbCategoryNewPost;
    private javax.swing.JComboBox<String> cbFormal;
    private javax.swing.JComboBox<String> cbShowFormal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFilter;
    private javax.swing.JLabel lblLetterCount;
    private javax.swing.JLabel lblMaxLetters;
    private javax.swing.JLabel lblNext;
    private javax.swing.JLabel lblPrevious;
    private javax.swing.JLabel lblShowing;
    private javax.swing.JLabel lblShowingAmount;
    private javax.swing.JLabel lblSlash;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalAmount;
    private javax.swing.JPanel pnlBlogPosts;
    private javax.swing.JPanel pnlBlogTab;
    private javax.swing.JPanel pnlNewPostTab;
    private testprojekt.BlogPostTemplate post1;
    private testprojekt.BlogPostTemplate post2;
    private testprojekt.BlogPostTemplate post3;
    private testprojekt.BlogPostTemplate post4;
    private testprojekt.BlogPostTemplate post5;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSok;
    private javax.swing.JTextArea txtText;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables

    
}
