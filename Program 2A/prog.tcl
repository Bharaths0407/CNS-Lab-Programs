set ns [new node]
set nf [open prog.tr w]
$ns trace-all $nf
set nd [open prog.nam w]
$ns namtrace-all $nd


proc finish { } {
    global ns nf nd
    $ns flush-trace
    close $nf
    close $nd
    exec nam prog.nam &
    exit 0 
}


set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]


$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n0 $n2 1Mb 10ms DropTail
$ns duplex-link $n0 $n3 1Mb 10ms DropTail
$ns duplex-link $n0 $n4 1Mb 10ms DropTail
$ns duplex-link $n0 $n5 1Mb 10ms DropTail
$ns duplex-link $n0 $n6 1Mb 10ms DropTail


Agent/Ping instproc recv {from rtt} {
    $self instvar node_
    puts "node[$node_ id] received ping messages from $from with round-trip-time $rtt"
}


set p1 [new Agent/Ping]
set p2 [new Agent/Ping]
set p3 [new Agent/Ping]
set p4 [new Agent/Ping]
set p5 [new Agent/Ping]
set p6 [new Agent/Ping]


$ns attach-agent $n1 $p1
$ns attach-agent $n2 $p2
$ns attach-agent $n3 $p3
$ns attach-agent $n4 $p4
$ns attach-agent $n5 $p5
$ns attach-agent $n6 $p6


$ns queue-limit $n0 $n4 3
$ns queue-limit $n0 $n5 2
$ns queue-limit $n0 $n6 2


$ns connect $p1 $p4
$ns connect $p2 $p5
$ns connect $p3 $p6


$ns at 0.2 "$p1 send"
$ns at 0.4 "$p2 send"
$ns at 0.6 "$p3 send"
$ns at 1.0 "$p4 send"
$ns at 1.2 "$p5 send"
$ns at 1.4 "$p6 send"
$ns at 2.0 "finish"
$ns run

ns prog.tcl -- to run
total 56 lines

BEGIN {
    count;
}
{
    event = $1;
    if(event == "d) {
        count++;
    }
}
END {
    printf("The total packets dropped %d\n",count);
}
awk -f prog.awk prog.tr

total 56 lines