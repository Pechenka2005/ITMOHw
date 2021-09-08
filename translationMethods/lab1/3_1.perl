$begin = 0;
$emptyCount = 0;

while (<>) {
    if (!(/^\s+$/ && $begin == 0)) {
        $begin = 1
    }

    if ($begin == 1) {
        if (/^\s+$/) {
            $emptyCount = $emptyCount + 1; 
        } else {
            if ($emptyCount > 0) {
                print "\n";
                $emptyCount = 0;
            } 
            s/^\s+//;
            s/\s+$//;
            s/(\s)(\s*)/$1/g;
            print;
            print "\n"
        }
    }
}