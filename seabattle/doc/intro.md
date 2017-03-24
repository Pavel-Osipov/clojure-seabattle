# Introduction to seabattle

;;  OK, let the board be a hashmap with vector keys and following values:
;;       :empty -- cell empty
;;       :ship-alive -- here is alive part of a ship
;;       :ship-dead -- here is dead part of ship
;;       :busy -- cell is near a ship, so another ship can't be placed here
;;
;;  Also, let the board be actually 12x12 with :busy values on the borders,
;;  just to save program's logic from decisions abut existence of the cells
;;
;;
;;  Also, let's keep a value of ships parts already hit, if this value reaches 4 + 3 + 3 + 2 + 2 + 2 + 1 + 1 + 1 + 1 = 20
;;  game is over
;;
;;
