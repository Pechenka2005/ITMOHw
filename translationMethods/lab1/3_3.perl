#!/usr/bin/perl
use List::MoreUtils qw(uniq);
use strict;
my @all = ();
my $pattern = 'secretWord';

while (my $line = <STDIN>) {
    my @arr = split(/$pattern/, $line);
    foreach my $arg (@arr) {
        $arg = $arg . $pattern;
        if ($arg =~ /<\s*(a)(.*)href\s*=\s*"(?<scheme>(.+?:)\/\/)?(?<host>\w+.*?)[\"\/\:].*>/) {
            my $maybeUrl = $+{host};
            if ($arg =~ /<*a*>/) {
                push(@all, $maybeUrl);
            }
        }
    }
}

@all = uniq(@all);
@all = sort(@all);
foreach my $url (@all) {
    print $url;
    print "\n";
}



