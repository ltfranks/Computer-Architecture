# Name: Jacob Andersen, Luke Franks
# Section: 1
# Description: exponentiation: x raised to the power of y. But only may use repeated addition.

# Java:
#    public static int exponentiation(int x, int y) {
#        int result = 1;
#        for (int i = 0; i < y; i++) {
#            int add = 0;
#            for (int j = 0; j < x; j++) {
#                add += result;
#            }
#            result = add;
#        }
#        return result;
#    }

.globl welcome
.globl prompt
.globl modText
.globl newlineText

.data
welcome:
	.asciiz " This program does exponentiation: x raised to the power of y. \n\n"
promptx:
	.asciiz " Enter an integer for x: "
prompty:
	.asciiz " Enter an integer for y: "
    
ResultText: 
	.asciiz " \n"
newlineText:
	.asciiz "\n"

.text
main:
	# Display the welcome message (load 4 into $v0 to display)
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	syscall

	# Display promptx
    li $v0, 4
    la $a0, promptx
    syscall

	# Read 1st integer from the user, x
	ori     $v0, $0, 5
	syscall

	# Temporarily store x in $t0
	or		$t0, $0, $v0
	
	# Display prompty
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, $a0, 0x5C
	syscall

	# Read 2nd integer, y
	ori	$v0, $0, 5			
	syscall

	# Temporarily store 2nd integer in $t1
	or		$t1, $0, $v0

    # set register t3 to 1
    ori     $t3, $0, 0
    addi    $t3, $t3, 1 

	# set register t4 to 0
    ori     $t4, $0, 0 


    #Exponentiation
	# $t0 = x
	# $t1 = y
	# $t3 = result
	# $s0 = i
	addi   $s0, $s0, -1
	# $s1 = j
	addi   $s1, $s1, 0
	j loop
    # $t4 = add
loop:
	addi    $s0, $s0, 1
    ori     $s1, $0, 0		    # reintialize j to 0
    bne		$s0, $t1, loop2		# if $s0 != y then goto loop2

	j exit

loop2:
	addi   $s1, $s1, 1
	bgt	   $s1, $t0, resultadd	# if   $s1 > x then goto loop
	add    $t4, $t4, $t3
	j loop2

resultadd:
    ori  $t3, $0, 0
    add  $t3, $t3, $t4   # result = add
    ori  $t4, $0, 0
    j loop

    
exit:
	# Display the Result text
	#ori     $v0, $0, 4			
	#lui     $a0, 0x1001
	#ori     $a0, $a0, 0x70
	#syscall
	
	# Display the Result
	ori     $v0, $0, 1			
	add 	$a0, $t3, $0
	syscall

	# Display a newline
	ori		$v0, $0, 4
	lui		$a0, 0x1001
	ori		$a0, $a0, 0x7A
	syscall
	
	# Exit (load 10 into $v0)
	ori     $v0, $0, 10
	syscall
