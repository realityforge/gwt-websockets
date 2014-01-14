# Ugly hack required as the gwt jars cause the javadoc tool heart ache
module Buildr
  module DocFix #:nodoc:
    include Extension
    after_define(:doc) do |project|
      project.doc.classpath.delete_if {|f| f.to_s =~ /.*\/com\/google\/gwt\/gwt-user\/.*/}
    end
  end
  class Project #:nodoc:
    include DocFix
  end
end
