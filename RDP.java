import java.io.*;

class RDP // Recursive Descent Parser
{
    char inp;

    public static void main(String[] args) throws IOException {
        InputStreamReader stdin = new InputStreamReader(System.in);
        RDP rdp = new RDP();
        rdp.parse();
    }

    void parse() {
        inp = getInp();
        S(); // Call start nonterminal
        if (inp == 'N')
            accept(); // end of string marker
        else
            reject();
    }

    void S() {
        if (inp == 'a') // apply rule 1
        {
            inp = getInp();
            System.out.println(inp);
            S();
            B();
        } // end rule 1
        else if (inp == 'b') {
            inp = getInp();
            System.out.println(inp);
        } // apply rule 2
        else
            reject();
    }

    void B() {
        if (inp == 'a')
            inp = getInp(); // rule 3
        else if (inp == 'b') // apply rule 4
        {
            inp = getInp();
            System.out.println(inp);
            B();
            if (inp == 'a') {
                inp = getInp();
                System.out.println(inp);
            } else
                reject();
        } // end rule 4
        else
            reject();
    }

    void accept() // Accept the input
    {
        System.out.println("accept");
    }

    void reject() // Reject the input
    {
        System.out.println("reject");
        System.exit(0); // terminate parser
    }

    char getInp() {
        try {
            return (char) System.in.read();
        } catch (IOException ioe) {
            System.out.println("IO error " + ioe);
        }
        return '#'; // must return a char
    }
}
