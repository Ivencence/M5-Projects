import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MovieCollectorManager {
    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        JFrame frame = new JFrame("Movie Library");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, movies);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, List<Movie> movieList) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        JTextField titleField = new JTextField();
        titleField.setBounds(100, 20, 165, 25);
        panel.add(titleField);

        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(10, 50, 80, 25);
        panel.add(genreLabel);

        JComboBox<String> genreComboBox = new JComboBox<>(new String[]{"Action", "Drama", "Comedy", "Romance"});
        genreComboBox.setBounds(100, 50, 165, 25);
        panel.add(genreComboBox);

        JButton addButton = new JButton("Add Movie");
        addButton.setBounds(10, 80, 120, 25);
        panel.add(addButton);

        JLabel countLabel = new JLabel("Total Movies:");
        countLabel.setBounds(10, 110, 100, 25);
        panel.add(countLabel);

        JTextArea countArea = new JTextArea();
        countArea.setBounds(120, 110, 50, 25);
        countArea.setEditable(false);
        panel.add(countArea);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Title", "Genre"}, 0);
        JTable movieTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(movieTable);
        scrollPane.setBounds(10, 140, 400, 200);
        panel.add(scrollPane);

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(10, 350, 100, 25);
        panel.add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(100, 350, 165, 25);
        panel.add(searchField);

        JButton searchButton = new JButton("Find Movie");
        searchButton.setBounds(270, 350, 120, 25);
        panel.add(searchButton);

        JTextArea searchResultArea = new JTextArea();
        searchResultArea.setBounds(10, 410, 400, 30);
        searchResultArea.setEditable(false);
        panel.add(searchResultArea);

        JComboBox<String> sortComboBox = new JComboBox<>(new String[]{"Title (A-Z)", "Genre"});
        sortComboBox.setBounds(10, 380, 150, 25);
        panel.add(sortComboBox);

        JButton sortButton = new JButton("Sort");
        sortButton.setBounds(170, 380, 80, 25);
        panel.add(sortButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = titleField.getText();
                if (!name.isEmpty()) {
                    movieList.add(new Movie(name, (String) genreComboBox.getSelectedItem()));
                    tableModel.addRow(new Object[]{name, genreComboBox.getSelectedItem()});
                    countArea.setText(String.valueOf(movieList.size()));
                    titleField.setText("");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = searchField.getText();
                boolean found = false;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (tableModel.getValueAt(i, 0).toString().equalsIgnoreCase(name)) {
                        found = true;
                        break;
                    }
                }
                if(found){
                searchResultArea.setText("Movie found.");}
                else{searchResultArea.setText("Movie not found.");}
            }
        });

        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sortOption = (String) sortComboBox.getSelectedItem();
                Collections.sort(movieList, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie m1, Movie m2) {
                        if (sortOption.equals("Title (A-Z)")) {
                            return m1.getTitle().compareToIgnoreCase(m2.getTitle());
                        } else {
                            return m1.getGenre().compareToIgnoreCase(m2.getGenre());
                        }
                    }
                });

                tableModel.setRowCount(0);
                for (Movie movie : movieList) {
                    tableModel.addRow(new Object[]{movie.getTitle(), movie.getGenre()});
                }
            }
        });
    }
}

class Movie {
    private String title;
    private String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}
