package game;

class Player {
        private String name;
        private int maxHealth;
        private int health;
        private int attack;
        private int defense;
        private int staminaPoints;
        private int magicPoints;
        private String playerMoves[];
        private double lightMoveDmg = .5;
        private double mediumMoveDmg = .75;
        private double heavyMoveDmg = 1;
        private double magicMoveDmg = 1.5;

        // Declaring player stat variables
        public Player(String name, int maxHealth, int attack, int defense, int magicPoints) {
            this.name = name;
            this.maxHealth = maxHealth;
            this.health = maxHealth;
            this.defense = defense;
            this.magicPoints = magicPoints;
            this.attack = attack;
        }

        // Getters Start
        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public double getAttack(){
            return attack;
        }

        public double getDamage(int moveNum) {
            double playerDamage = 0;
            switch (moveNum) {
                case 1:
                    playerDamage = attack * lightMoveDmg;
                    break;
                case 2:
                    playerDamage = attack * mediumMoveDmg;
                    break;
                case 3:
                    playerDamage = attack * heavyMoveDmg;
                    break;
                case 4:
                    playerDamage = attack * magicMoveDmg;
                    break;
                default:
                    System.out.println("Move no. out of bounds");
                    break;
                }
                return playerDamage;
        }

        public int getDefense() {
            return defense;
        }

        public int getMagicPoints() {
            return magicPoints;
        }
        // Getters End

        // Stat change methods start
        public void takeDamage(int damage) {
            double netdamage = damage - (damage * (defense + (Math.random() * 10)) / 100);
            health -= netdamage;
            if (health < 0) {
                health = 0;
            }
        }

        public void heal(int amount) {
            health += amount;
            if (health > maxHealth) {
                health = maxHealth;
            }
        }

        public void increaseDefense(int amount) {
            defense += amount;
        }

        public void decreaseDefense(int amount) {
            defense -= amount;
            if (defense < 0) {
                defense = 0;
            }
        }

        public void increasestaminaPoints(int amount) {
            staminaPoints += amount;
        }

        public void decreasestaminaPoints(int amount) {
            staminaPoints -= amount;
            if (staminaPoints < 0) {
                staminaPoints = 0;
            }
        }

        public void increaseMagicPoints(int amount) {
            magicPoints += amount;
        }

        public void decreaseMagicPoints(int amount) {
            magicPoints -= amount;
            if (magicPoints < 0) {
                magicPoints = 0;
            }
        }
        // Stat change methods end

        // Display Stat Method
        public void displayStats() {
            System.out.println("Player Stats:");
            System.out.println("Name: " + name);
            System.out.println("Health: " + health + "/" + maxHealth);
            System.out.println("Defense: " + defense);
            System.out.println("Special Points: " + staminaPoints);
            System.out.println("Magic Points: " + magicPoints);
        }

        public void setMoves(String lightMove, String mediumMove, String heavyMove, String magicMove) {
            playerMoves[0] = lightMove;
            playerMoves[1] = mediumMove;
            playerMoves[2] = heavyMove;
            playerMoves[3] = magicMove;
        }
    }
