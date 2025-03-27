#! /bin/bash
DEPS_DIR="deps"
mkdir -p "$DEPS_DIR"

# Dependences
HERMES_JAR="$DEPS_DIR/HermesAgent.jar"
CONCIERGE_JAR="$DEPS_DIR/ConciergeAgent.jar"

# Download Deps
HERMES_URL="https://github.com/chon-group/Hermes/releases/latest/download/HermesAgent.jar"
CONCIERGE_URL="https://github.com/chon-group/Concierge/releases/latest/download/ConciergeAgent.jar"

download_if_not_exists() {
    local file="$1"
    local url="$2"
    
    if [ -f "$file" ]; then
        echo "$file existis."
    else
        echo "Downloading $file..."
        wget "$url" -O "$file"
    fi
}
download_if_not_exists "$HERMES_JAR" "$HERMES_URL"
download_if_not_exists "$CONCIERGE_JAR" "$CONCIERGE_URL"

# recovering spock.asl
rm -f mas01/src/agt/spock.asl
cp mutualism/asl/spock.asl.bkp mutualism/asl/spock.asl

# Executing the example
jason mas01/mas01.mas2j &
jason mutualism/mutualism.mas2j