package game;

public class Enemy {

    private String enemyName;
    private double enemyHealth = 100;
    private int enemyAttack;
    private int enemyDefense;
    private String enemyMoves[] = new String[3];
    private double lightMoveDmg = .5;
    private double mediumMoveDmg = .75;
    private double heavyMoveDmg = 1;


    public Enemy(String enemyName, double enemyHealth, int enemyAttack, int enemyDefense, String enemyMoves[])
    {
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
        this.enemyAttack = enemyAttack;
        this.enemyDefense = enemyDefense;
        this.enemyMoves[0] = enemyMoves[0];
        this.enemyMoves[1] = enemyMoves[1];
        this.enemyMoves[2] = enemyMoves[2];
    }

    // Display all stats for an enemy

    public void displayStats()
    {
        System.out.println(enemyName + " -");
        System.out.println("Health = " + enemyHealth);
        System.out.println("Attack = " + enemyAttack);
        System.out.println("Defense = " + enemyDefense);
        System.out.println("Health = " + enemyHealth);
        System.out.println("Moves - ");
        System.out.println(enemyMoves[0]);
        System.out.println(enemyMoves[1]);
        System.out.println(enemyMoves[2]);
    }

    // Setter functions for all variables

    public void setName(String newName)
    {
        enemyName = newName;
    }
    public void setHealth(int newHealth)
    {
        enemyHealth = newHealth;
    }
    public void setAttack(int newAttack)
    {
        enemyAttack = newAttack;
    }
    public void setDefense(int newDefense)
    {
        enemyDefense = newDefense;
    }
    public void setMoves(String lightMove, String mediumMove, String heavyMove)
    {
        enemyMoves[0] = lightMove;
        enemyMoves[1] = mediumMove;
        enemyMoves[2] = heavyMove;
    }

    // Getter functions for all variables

    public String getName()
    {
        return enemyName;
    }
    public double getHealth()
    {
        return enemyHealth;
    }
    public int getAttack()
    {
        return enemyAttack;
    }
    public int getDefense()
    {
        return enemyDefense;
    }
    public String[] getMoves()
    {
        return enemyMoves;
    }

    public double getDamage(int moveNo)
    {
        double rawDamage = 0;
        switch (moveNo) {
            case 0:
                rawDamage = enemyAttack * lightMoveDmg;
                break;
            case 1:
                rawDamage = enemyAttack * mediumMoveDmg;
                break;
            case 2:
                rawDamage = enemyAttack * heavyMoveDmg;
                break;
            default:
                System.out.println("Move no. out of bounds");
                break;
        }
        return rawDamage;
    }

    public void takeDamage(double rawDamage)
    {
        double damage = rawDamage - (rawDamage*(enemyDefense+(Math.random()*10))/100);
        enemyHealth -= damage;
        if (enemyHealth < 0) {
            enemyHealth = 0;
        }
        //setHealth(enemyHealth);
    }

    public Boolean isAlive()
    {
        if(enemyHealth>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
