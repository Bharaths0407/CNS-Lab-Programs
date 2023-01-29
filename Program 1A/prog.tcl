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
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail
$ns queue-limit $n1 $n2 10
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
set cbr0 [new Application/Traffic/CBR]
$cbr0 set interval_ 0.005
$cbr0 set packetSize_ 500
$cbr0 attach-agent $udp0
set sink [new Agent/Null]
$ns attach-agent $n2 $sink
$ns connect $udp0 $sink
$ns at 0.5 "$cbr0 start"
$ns at 4.5 "$cbr0 stop"
$ns at 5.0 "finish"
$ns run

ns prg.tcl -- to run

BEGIN{
    dcount = 0;
    rcount = 0;
}
{
    event = $1;
    if(event == "d") {
        dcount++;
    }
    if(event == "r") {
        rcount++;
    }
}
END{
    printf("The dropped packet number is %d\n", dcount);
    printf("The received packet number is %d\n", rcount);
}

awk -f prog.awk prog.tr -- to run