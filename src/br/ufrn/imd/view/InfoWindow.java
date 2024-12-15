package br.ufrn.imd.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class InfoWindow extends JFrame {
    private JComboBox<String> comboBox;
    private JPanel infoPanel;
    private HashMap<String, String[]> algorithmsInfo;

    public InfoWindow() {
        setTitle("Informações sobre Algoritmos de Ordenação");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initData();
        initUI();
    }

    private void initData() {
        // Inicializa os dados dos algoritmos com descrições mais detalhadas
        algorithmsInfo = new HashMap<>();

        algorithmsInfo.put("Bubble Sort", new String[]{
                "Descrição Geral:\n" +
                "O Bubble Sort é um dos algoritmos de ordenação mais simples e intuitivos. Ele recebe esse nome " +
                "porque, a cada passagem, o maior elemento ainda não posicionado \"borbulha\" até a posição correta. " +
                "A ideia é percorrer o array repetidamente, comparando elementos adjacentes e trocando-os se estiverem " +
                "fora de ordem, até que não haja mais trocas.\n\n" +

                "Como Funciona:\n" +
                "1. Percorra a lista, comparando elementos adjacentes.\n" +
                "2. Se um elemento atual for maior que o próximo (para ordem crescente), troque-os.\n" +
                "3. Ao final da primeira passagem, o maior elemento estará no final do array.\n" +
                "4. Repita ignorando o último elemento já ordenado.\n" +
                "5. Pare quando percorrer a lista sem realizar trocas.\n\n" +

                "Complexidade:\n" +
                "Tempo: O(n²) no pior e médio caso, O(n) no melhor caso (se já estiver ordenado com verificação de troca).\n" +
                "Espaço: O(1).\n" +
                "Estabilidade: Estável, mantém a ordem de elementos iguais.\n\n" +

                "Aplicações e Observações:\n" +
                "Pouco usado na prática devido à ineficiência, é útil principalmente para fins educacionais.",
        });

        algorithmsInfo.put("Merge Sort", new String[]{
                "Descrição Geral:\n" +
                "O Merge Sort é um algoritmo baseado no método \"dividir e conquistar\". Ele divide o array em " +
                "metades até que cada sublista tenha apenas um elemento (automaticamente ordenada), e então mescla " +
                "essas sublistas ordenadas, resultando em uma lista final totalmente ordenada.\n\n" +

                "Como Funciona:\n" +
                "1. Divida a lista em duas metades.\n" +
                "2. Ordene cada metade recursivamente.\n" +
                "3. Mescle as duas metades ordenadas, comparando os elementos e inserindo o menor primeiro.\n\n" +

                "Complexidade:\n" +
                "Tempo: O(n log n) no pior, médio e melhor caso.\n" +
                "Espaço: O(n), pois requer espaço adicional para mesclagem.\n" +
                "Estabilidade: Estável, preserva a ordem de elementos iguais.\n\n" +

                "Aplicações e Observações:\n" +
                "Excelente para grandes quantidades de dados devido à complexidade previsível. " +
                "É amplamente utilizado em contextos onde a estabilidade é importante e o espaço extra não é um problema.",
        });

        algorithmsInfo.put("Bogo Sort", new String[]{
                "Descrição Geral:\n" +
                "O Bogo Sort é provavelmente o algoritmo de ordenação menos eficiente já concebido. Ele se baseia em " +
                "tentar a sorte: checa se a lista está ordenada; se não, embaralha todos os elementos aleatoriamente " +
                "e repete até que, por acaso, a lista apareça ordenada.\n\n" +

                "Como Funciona:\n" +
                "1. Verifique se a lista está ordenada.\n" +
                "2. Se não estiver, embaralhe a lista completamente.\n" +
                "3. Repita até que a lista esteja ordenada.\n\n" +

                "Complexidade:\n" +
                "Tempo: O((n-1)!), que é gigantesco mesmo para pequenos n.\n" +
                "Espaço: O(1), não precisa de espaço extra além da própria lista.\n" +
                "Estabilidade: Não aplicável, pois o algoritmo é baseado em permutações aleatórias.\n\n" +

                "Aplicações e Observações:\n" +
                "Não é usado na prática devido à sua completa ineficiência. Serve apenas como curiosidade ou piada " +
                "teórica, demonstrando a importância de se usar métodos apropriados."
        });

        algorithmsInfo.put("Quick Sort", new String[]{
                "Descrição Geral:\n" +
                "O QuickSort é um algoritmo de ordenação baseado na estratégia de \"dividir e conquistar\". " +
                "Ele escolhe um pivô e particiona a lista em torno desse pivô, colocando todos os elementos menores à esquerda " +
                "e todos os maiores à direita. Em seguida, ordena recursivamente cada sublista resultante.\n\n" +

                "Como Funciona:\n" +
                "1. Escolha um pivô (por exemplo, o primeiro, o último ou um elemento aleatório).\n" +
                "2. Particione a lista de modo que todos os elementos menores que o pivô fiquem antes dele, e todos os maiores depois.\n" +
                "3. Aplique o QuickSort recursivamente às sublistas à esquerda e à direita do pivô.\n\n" +

                "Complexidade:\n" +
                "Tempo: O(n log n) em média, mas pode chegar a O(n²) no pior caso.\n" +
                "Espaço: O(log n) devido à profundidade da recursão.\n" +
                "Estabilidade: Não é estável, pois a posição relativa de elementos iguais pode mudar.\n\n" +

                "Aplicações e Observações:\n" +
                "Muito eficiente na prática, costuma ser um dos algoritmos mais rápidos, embora a escolha do pivô seja fundamental. " +
                "Não requer espaço adicional proporcional a n e tem bom aproveitamento de cache."
        });

        algorithmsInfo.put("Selection Sort", new String[]{
                "Descrição Geral:\n" +
                "O Selection Sort encontra repetidamente o menor elemento da parte não ordenada da lista e o coloca na sua posição correta. " +
                "Diferente do Bubble Sort, ele minimiza o número de trocas, mas ainda assim é ineficiente para grandes listas.\n\n" +

                "Como Funciona:\n" +
                "1. Encontre o menor elemento na lista inteira.\n" +
                "2. Troque-o com o primeiro elemento.\n" +
                "3. Agora considere o primeiro elemento como ordenado e repita o processo para o restante da lista.\n\n" +

                "Complexidade:\n" +
                "Tempo: O(n²) no pior, médio e melhor caso.\n" +
                "Espaço: O(1), já que é in-place.\n" +
                "Estabilidade: Não é estável, pois trocas podem alterar a ordem relativa de elementos iguais.\n\n" +

                "Aplicações e Observações:\n" +
                "Pouco usado na prática devido à baixa eficiência. Pode ser útil se o custo de trocas for muito alto, pois minimiza o número de trocas."
        });

        algorithmsInfo.put("Insertion Sort", new String[]{
                "Descrição Geral:\n" +
                "O Insertion Sort insere cada novo elemento na posição apropriada entre os já ordenados, " +
                "funcionando de maneira semelhante a como organizamos cartas na mão.\n\n" +

                "Como Funciona:\n" +
                "1. Considere o primeiro elemento ordenado por si só.\n" +
                "2. Pegue o próximo elemento e insira-o na posição correta em relação aos já ordenados, " +
                "deslocando elementos maiores para a direita.\n" +
                "3. Repita até que todos os elementos estejam ordenados.\n\n" +

                "Complexidade:\n" +
                "Tempo: O(n²) no pior e médio caso, O(n) no melhor caso (lista já ordenada).\n" +
                "Espaço: O(1), in-place.\n" +
                "Estabilidade: Estável, mantém a ordem relativa de elementos iguais.\n\n" +

                "Aplicações e Observações:\n" +
                "Eficaz para listas pequenas ou quase ordenadas. Simples de implementar e bom para cenários onde se insere poucos elementos em uma lista já ordenada."
        });

        algorithmsInfo.put("Heap Sort", new String[]{
                "Descrição Geral:\n" +
                "O HeapSort transforma o array em um heap máximo, de forma que o maior elemento fique na raiz. " +
                "Em seguida, extrai repetidamente o maior elemento, colocando-o na posição correta no final do array.\n\n" +

                "Como Funciona:\n" +
                "1. Construa um heap máximo a partir da lista.\n" +
                "2. Troque o maior elemento (raiz do heap) com o último elemento do array.\n" +
                "3. Reduza o tamanho do heap em 1 e reajuste-o (heapify) para restaurar as propriedades do heap.\n" +
                "4. Repita até que todos os elementos estejam em posição ordenada.\n\n" +

                "Complexidade:\n" +
                "Tempo: O(n log n) no pior, médio e melhor caso.\n" +
                "Espaço: O(1), é in-place.\n" +
                "Estabilidade: Não é estável, pois a reordenação no heap pode alterar a posição relativa de elementos iguais.\n\n" +

                "Aplicações e Observações:\n" +
                "Garante bom desempenho no pior caso e não requer espaço extra significativo. Entretanto, na prática, costuma ser mais lento que o QuickSort devido ao acesso menos local."
        });

        algorithmsInfo.put("Shell Sort", new String[]{
                "Descrição Geral:\n" +
                "O ShellSort é uma melhoria sobre o Insertion Sort. Ele primeiramente ordena elementos distantes, " +
                "usando incrementos (gaps) que diminuem a cada iteração, até chegar a um gap de 1, onde uma passada final " +
                "de Insertion Sort ordena completamente a lista.\n\n" +

                "Como Funciona:\n" +
                "1. Escolha uma sequência de gaps (por exemplo, n/2, n/4, ... até 1).\n" +
                "2. Para cada gap, aplique uma espécie de Insertion Sort em sublistas formadas por elementos separados pelo gap.\n" +
                "3. Reduza o gap e repita, tornando a lista cada vez mais ordenada.\n\n" +

                "Complexidade:\n" +
                "Tempo: Varia conforme a sequência de gaps. Em geral, O(n²) no pior caso. Na prática, muitas implementações " +
                "são bem rápidas e superam facilmente o Insertion Sort.\n" +
                "Espaço: O(1), in-place.\n" +
                "Estabilidade: Não é estável, pois a reordenação a longas distâncias pode alterar a ordem relativa de elementos iguais.\n\n" +

                "Aplicações e Observações:\n" +
                "Simples, eficiente para tamanhos médios e fácil de implementar. Geralmente mais rápido que o Insertion Sort puro e requer pouco esforço para codificação."
        });

        algorithmsInfo.put("Radix Sort", new String[]{
                "Descrição Geral:\n" +
                "O Radix Sort ordena números analisando-os dígito a dígito, da posição menos significativa para a mais significativa, " +
                "usando um algoritmo de classificação estável (como Counting Sort) em cada posição.\n\n" +

                "Como Funciona:\n" +
                "1. Defina a base (por exemplo, 10 para dígitos decimais).\n" +
                "2. Ordene a lista de acordo com o dígito menos significativo, usando um algoritmo estável.\n" +
                "3. Repita para o próximo dígito mais significativo, e assim por diante, até processar todos os dígitos.\n\n" +

                "Complexidade:\n" +
                "Tempo: O(d*(n+k)), onde d é o número de dígitos, n é o número de elementos e k é o tamanho da base. Para bases e d fixos, " +
                "aproxima-se de O(n).\n" +
                "Espaço: O(n + k), pois requer arrays auxiliares para o Counting Sort.\n" +
                "Estabilidade: Estável, assumindo que o método de contagem seja estável.\n\n" +

                "Aplicações e Observações:\n" +
                "Excelente para ordenar inteiros com tamanho de chave fixo e relativamente pequeno. Pode superar algoritmos baseados em comparação " +
                "quando n é muito grande, mas é mais restrito ao tipo de dado e requer espaço extra."
        });

    }

    private void initUI() {
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Painel superior com JComboBox e botão
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboBox = new JComboBox<>(new String[]{"Bubble Sort", "Merge Sort", "Bogo Sort", "Quick Sort", "Selection Sort", "Insertion Sort", "Heap Sort", "Shell Sort", "Radix Sort"});
        JButton showInfoButton = new JButton("Ver informações");
        JButton nextButton = new JButton("Próximo");
        nextButton.setVisible(false);
        JButton backToHomeButton = new JButton("Voltar");

        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = (String) comboBox.getSelectedItem();
                nextButton.setVisible(true);
                updateInfoPanel(selectedAlgorithm);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlgorithm = comboBox.getItemAt(comboBox.getSelectedIndex()+1);
                if (comboBox.getSelectedIndex() == comboBox.getItemCount()-1) {
                    comboBox.setSelectedIndex(0);
                    selectedAlgorithm = (String) comboBox.getSelectedItem();
                } else {
                    comboBox.setSelectedIndex(comboBox.getSelectedIndex() + 1);
                }
                updateInfoPanel(selectedAlgorithm);
            }
        });

        backToHomeButton.addActionListener(e -> {
            new MainMenu().setVisible(true);
            dispose();
        });

        topPanel.add(new JLabel("Selecione um algoritmo: "));
        topPanel.add(comboBox);
        topPanel.add(showInfoButton);
        topPanel.add(nextButton);
        topPanel.add(backToHomeButton);

        // Painel onde as informações do algoritmo serão exibidas
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Inicialmente vazio até o usuário clicar em "Ver informações"
        JTextArea initialText = new JTextArea("Selecione um algoritmo e clique em 'Ver informações' para exibir detalhes.");
        initialText.setEditable(false);
        initialText.setWrapStyleWord(true);
        initialText.setLineWrap(true);
        infoPanel.add(initialText, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(infoPanel), BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }

    private void updateInfoPanel(String algorithm) {
        infoPanel.removeAll(); // Remove o que tinha anteriormente
        String[] info = algorithmsInfo.get(algorithm);
        if(info != null) {
            JTextArea textArea = new JTextArea(info[0]);
            textArea.setEditable(false);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setCaretPosition(0);
            infoPanel.add(textArea, BorderLayout.CENTER);
        } else {
            JTextArea textArea = new JTextArea("Informações não disponíveis.");
            textArea.setEditable(false);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setCaretPosition(0);
            infoPanel.add(textArea, BorderLayout.CENTER);
        }

        infoPanel.revalidate();
        infoPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InfoWindow infoWindow = new InfoWindow();
            infoWindow.setVisible(true);
        });
    }
}
