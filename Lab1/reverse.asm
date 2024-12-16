# Name: Jacob Andersen, Luke Franks
# Section: 1
# Description: Print a number that represents the reverse-ordered binary of the input
#              number. 

#11001 -> 10011
# Let there be a result number and the input 32-bit number
# 1. Left shift result by 1
# 2. Look at the last bit of input
#    - if its a 1, add 1 to result
#    - if its a 0, add 0 to result
# 3. Right shift input by 1

# Java:
#    public static int reverseBits(int input)
#    {
#        int Result = 0;
#        int totBits = 32;
#        int i = 0;
#        while (i < totBits){
#            Result = Result << 1;  // left shift by 1
#            Result = Result | (input & 1);
#            input = input >> 1;
#            i++;
#        }
#        return Result;
#    }


.globl welcome
.globl prompt
.globl modText
.globl newlineText

.data
welcome:
	.asciiz " This program reverses bits of a 32-bit input number. \n\n"
prompt:
	.asciiz " Enter a positive integer: "
newlineText:
	.asciiz "\n"

.text
main:
	# Display the welcome message (load 4 into $v0 to display)
    li $v0, 4
    la $a0, welcome
    syscall

	# Display prompt
    li $v0, 4
    la $a0, prompt
    syscall

	# Read integer from the user
	ori     $v0, $0, 5
	syscall

	# Temporarily store input in $t0
	or		$t0, $0, $v0

	# initializing registers
    ori     $s0, $0, 0
    addi    $s0, $s0, 32		# totBits = 32
	ori		$t1, $t1, 0			# Result = 0
	ori		$t2, $0, 0			# i = 0

	
# while (i < totBits)
loop:
	sll		$t1, $t1, 1			# $t1 = $t1 << 0
	andi	$t3, $t0, 1			# (input & 1)
	or		$t1, $t1, $t3		# $t1 = $t1 | $t3
	srl		$t0, $t0, 1			# $t0 = $t0 >> 1
	addi	$t2, $t2, 1			# i++
	bne		$t2, $s0, loop		# if i != 32 then loop
	
exit:	

	# Display the Result
	ori     $v0, $0, 1			
	add 	$a0, $t1, $0
	syscall
	
	# Exit (load 10 into $v0)
	ori     $v0, $0, 10
	syscall

