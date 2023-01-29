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
set n7 [$ns node]
set n8 [$ns node]


$ns duplex-link $n0 $n1 2Mb 10ms DropTail
$ns duplex-link $n0 $n2 2Mb 10ms DropTail
$ns duplex-link $n0 $n3 1Mb 20ms DropTail

$ns make-lan "$n3 $n4 $n5 $n6 $n7 $n8" 512kb 40ms LL Queue/DropTail Mac/802_3

$ns duplex-link-op $n0 $n1 orient right-down
$ns duplex-link-op $n0 $n2 orient right-up
$ns duplex-link-op $n0 $n3 orient right

$ns queue-limit $n0 $n3 20

set tcp0 [new Agent/TCP/Vegas]
$ns attach-agent $n1 $tcp0
$tcp0 set class_ 1
$tcp0 set packetSize_ 55
set sink0 [new Agent/TCPSink]
$ns attach-agent $n7 $sink0
$ns connect $tcp0 $sink0

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

set tfile0 [open cwnd.tr w]
$tcp0 attach $tfile0
$tcp0 trace cwnd_

set tcp1 [new Agent/TCP/Vegas]
$ns attach-agent $n2 $tcp1
$tcp1 set class_ 2
$tcp1 set packetSize_ 55
set sink1 [new Agent/TCPSink]
$ns attach-agent $n8 $sink0
$ns connect $tcp1 $sink1

set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1

set tfile1 [open cwnd2.tr w]
$tcp1 attach $tfile1
$tcp1 trace cwnd_

$ns at 0.5 "$ftp0 start"
$ns at 1.0 "$ftp1 start"
$ns at 4.5 "$ftp0 stop"
$ns at 4.5 "$ftp1 stop"
$ns at 5.0 "finish"
$ns run

ns prog.tcl -- to run
// 60 lines in this

BEGIN {

}
{
    event = $1;
    if($6 == "cwnd_") {
        printf("%f\t%f\n",$1, $7);
    }
}
END {

}

awk -f prog.awk cwnd.tr > a1
awk -f prog.awk cwnd2.tr > a2
xgraph a1 a2